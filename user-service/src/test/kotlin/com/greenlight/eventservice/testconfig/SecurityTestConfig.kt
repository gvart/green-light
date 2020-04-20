package com.greenlight.eventservice.testconfig

import com.greenlight.common.security.service.SecurityService
import com.greenlight.eventservice.config.SecurityConfig
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Primary
import reactor.core.publisher.Mono

@TestConfiguration
@Import(SecurityConfig::class)
class SecurityTestConfig {

    @Bean
    @Primary
    fun mockSecurityService(): SecurityService {
        return object : SecurityService {
            override fun extractUserId() = Mono.just("1")
            override fun extractUsername() = Mono.just("username")
        }
    }
}