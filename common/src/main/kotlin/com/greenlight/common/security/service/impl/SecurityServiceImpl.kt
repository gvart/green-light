package com.greenlight.common.security.service.impl

import com.greenlight.common.security.service.SecurityService
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class SecurityServiceImpl : SecurityService {
    override fun extractUserId(): Mono<String> {
        return extractFromClaims("sub")
    }

    override fun extractUsername(): Mono<String> {
        return extractFromClaims("name")
    }

    private fun extractFromClaims(key: String): Mono<String> {
        return ReactiveSecurityContextHolder.getContext()
            .map { it.authentication as JwtAuthenticationToken }
            .map { (it.principal as Jwt).claims[key].toString() }
            .doOnError { throw  AuthenticationCredentialsNotFoundException("User not authenticated.") }
    }
}