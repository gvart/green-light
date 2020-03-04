package com.greenlight.common.security.service.impl

import com.greenlight.common.security.service.SecurityService
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class SecurityServiceImpl : SecurityService {
    override fun extractUserId(): Mono<Long> {
        return ReactiveSecurityContextHolder.getContext()
            .map { it.authentication as OAuth2Authentication }
            .map { getUserId(it) }
            .doOnError { throw  AuthenticationCredentialsNotFoundException("User not authenticated.") }
    }

    /**
     * TODO refactor
     */
    private fun getUserId(auth: OAuth2Authentication): Long {
        if (auth.userAuthentication is UsernamePasswordAuthenticationToken) {
            return (((auth.userAuthentication.details as Map<String, Any>)["principal"] as Map<String, Any>)["id"] as Int).toLong()
        }
        throw UnsupportedOperationException("Authentication is not extracted.")
    }
}