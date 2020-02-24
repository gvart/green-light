package com.greenlight.eventservice.config

import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@EnableWebFluxSecurity
class SecurityConfig(private val authenticationManager: ReactiveAuthenticationManager) {

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .csrf().disable()
            .authorizeExchange {
                it.pathMatchers("/**").authenticated()

            }
            .oauth2ResourceServer {
                it.jwt { jwt ->
                    jwt.authenticationManager(authenticationManager)
                }
            }
            .build()
    }


}