package com.greenlight.userservice.testconfig

import com.greenlight.common.security.service.SecurityService
import com.greenlight.userservice.config.SecurityConfig
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.security.authentication.ReactiveAuthenticationManager
import reactor.core.publisher.Mono

@TestConfiguration
@Import(SecurityConfig::class)
class SecurityTestConfig {

    @Bean
    fun mockSecurityService(): SecurityService {
        return object : SecurityService {
            override fun extractUserId() = Mono.just(1L)
        }
    }

    @MockBean
    private lateinit var authenticationManager: ReactiveAuthenticationManager
}