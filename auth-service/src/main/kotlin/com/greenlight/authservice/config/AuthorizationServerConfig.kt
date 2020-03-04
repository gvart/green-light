package com.greenlight.authservice.config

import com.greenlight.authservice.security.UserDetailsServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter

@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig(
    private val tokenStore: TokenStore,
    private val clientDetailsService: ClientDetailsService,
    private val authenticationManager: AuthenticationManager,
    private val accessTokenConverter: JwtAccessTokenConverter,
//    private val approvalStore: ApprovalStore,
    private val userDetailsService: UserDetailsServiceImpl
) : AuthorizationServerConfigurerAdapter() {

    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.withClientDetails(clientDetailsService)
    }

    override fun configure(security: AuthorizationServerSecurityConfigurer) {
        security.tokenKeyAccess("permitAll()")
            .checkTokenAccess("isAuthenticated()")
            .allowFormAuthenticationForClients()
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        endpoints/*.approvalStore(approvalStore)*/
            .tokenEnhancer(accessTokenConverter)
            .authenticationManager(authenticationManager)
            .tokenStore(tokenStore)
            .userDetailsService(userDetailsService)
    }


    @Bean
    fun authenticationConverter(userDetailsService: UserDetailsServiceImpl): UserAuthenticationConverter {
        val authenticationConverter = DefaultUserAuthenticationConverter()
        authenticationConverter.setUserDetailsService(userDetailsService)
        return authenticationConverter
    }


}