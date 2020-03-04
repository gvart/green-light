package com.greenlight.common.security.resourceserver

import com.greenlight.common.security.JwtProperties
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.ReactiveAuthenticationManagerAdapter
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import org.springframework.util.StreamUtils
import java.nio.charset.Charset

@Configuration
@ConditionalOnProperty(value = ["greenlight.security.oauth2.resource.auto-configure"], havingValue = "true")
@EnableConfigurationProperties(GLResourceServerProperties::class, JwtProperties::class)
class ResourceServerAutoConfig(
    private val resourceServerProperties: ResourceServerProperties,
    private val clientProperties: OAuth2ClientProperties,
    private val jwtProperties: JwtProperties
) {


    @Bean
    @ConfigurationProperties(prefix = "security.oauth2.client")
    fun clientCredentialsResourceDetails(): ClientCredentialsResourceDetails {
        return ClientCredentialsResourceDetails()
    }

    @Bean
    @LoadBalanced
    fun oAuth2RestTemplate(): OAuth2RestTemplate {
        return OAuth2RestTemplate(clientCredentialsResourceDetails())
    }

    @Bean
    fun tokenServices(): ResourceServerTokenServices {
        return CustomUserInfoTokenServices(resourceServerProperties.userInfoUri, oAuth2RestTemplate())
    }

    @Bean
    fun tokenStore(): JwtTokenStore {
        return JwtTokenStore(jwtAccessTokenConverter())
    }

    @Bean
    fun jwtAccessTokenConverter(): JwtAccessTokenConverter {
        val converter = JwtAccessTokenConverter()
        converter.setVerifierKey(getStringContentOfPublicKey())
        return converter
    }

    private fun getStringContentOfPublicKey(): String =
        StreamUtils.copyToString(jwtProperties.publicKey.inputStream, Charset.defaultCharset())

    @Bean
    fun oAuth2AuthenticationManager(): OAuth2AuthenticationManager {
        val oAuth2AuthenticationManager = OAuth2AuthenticationManager()
        oAuth2AuthenticationManager.setResourceId(resourceServerProperties.id)
        oAuth2AuthenticationManager.setTokenServices(tokenServices())
        return oAuth2AuthenticationManager
    }

    @Bean
    fun reactiveAuthenticationManager(): ReactiveAuthenticationManager {
        return ReactiveAuthenticationManagerAdapter(oAuth2AuthenticationManager())
    }
}