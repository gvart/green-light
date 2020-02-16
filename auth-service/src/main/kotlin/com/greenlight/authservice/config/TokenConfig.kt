package com.greenlight.authservice.config

import com.greenlight.authservice.security.ClientDetailsServiceImpl
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import javax.sql.DataSource

@Configuration
class TokenConfig(
    @Value("\${security.oauth2.validity.access-token}") private val accessTokenValidity: Int,
    @Value("\${security.oauth2.validity.access-token}") private val refreshTokenValidity: Int
) {

    @Bean
    fun tokenStore(dataSource: DataSource): TokenStore {
        return JdbcTokenStore(dataSource)
    }

    @Bean
    fun accessTokenConverter(): JwtAccessTokenConverter {
        val converter = JwtAccessTokenConverter()
//        converter.setSigningKey("private key")
//        converter.setVerifierKey("public key")
        return converter
    }

    @Bean
    @Primary
    fun defaultTokenServices(
        tokenStore: TokenStore,
        authenticationManager: AuthenticationManager
    ): DefaultTokenServices {
        val tokenServices = DefaultTokenServices()
        tokenServices.setTokenStore(tokenStore)
        tokenServices.setTokenEnhancer(accessTokenConverter())
        tokenServices.setAuthenticationManager(authenticationManager)
        tokenServices.setSupportRefreshToken(true)
        tokenServices.setAccessTokenValiditySeconds(accessTokenValidity)
        tokenServices.setRefreshTokenValiditySeconds(refreshTokenValidity)
        return tokenServices
    }

    @Bean
    fun approvalStore(tokenStore: TokenStore): TokenApprovalStore {
        val approvalStore = TokenApprovalStore()
        approvalStore.setTokenStore(tokenStore)
        return approvalStore
    }

    @Bean
    fun userApprovalTokenStore(
        tokenStore: TokenStore,
        clientDetailsService: ClientDetailsServiceImpl
    ): TokenStoreUserApprovalHandler {
        val approvalHandler = TokenStoreUserApprovalHandler()
        approvalHandler.setTokenStore(tokenStore)
        approvalHandler.setRequestFactory(DefaultOAuth2RequestFactory(clientDetailsService))
        approvalHandler.setClientDetailsService(clientDetailsService)
        return approvalHandler
    }
}