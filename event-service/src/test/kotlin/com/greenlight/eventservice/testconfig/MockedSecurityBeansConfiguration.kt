package com.greenlight.eventservice.testconfig

import com.greenlight.common.security.service.SecurityService
import org.mockito.Mockito.mock
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder
import reactor.core.publisher.Mono

@TestConfiguration
class MockedSecurityBeansConfiguration {

    @Bean
    @Primary
    fun fakedSecurityService(): SecurityService {
        return object : SecurityService {
            override fun extractUserId() = Mono.just("userId")
            override fun extractUsername() = Mono.just("username")

        }
    }

    @MockBean
    lateinit var decoder: ReactiveJwtDecoder
}