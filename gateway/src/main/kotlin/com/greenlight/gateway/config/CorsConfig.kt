package com.greenlight.gateway.config

import org.springframework.cloud.gateway.handler.RoutePredicateHandlerMapping
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.web.cors.CorsConfiguration
import java.util.*

@Configuration
class CorsConfig {
    @Bean
    fun corsConfiguration(routePredicateHandlerMapping: RoutePredicateHandlerMapping): CorsConfiguration? {
        val corsConfiguration = CorsConfiguration().applyPermitDefaultValues()

        corsConfiguration.allowedMethods = listOf(
            HttpMethod.OPTIONS,
            HttpMethod.PUT,
            HttpMethod.GET,
            HttpMethod.DELETE,
            HttpMethod.POST
        ).map { it.name }

        corsConfiguration.addAllowedOrigin("*")
        routePredicateHandlerMapping.setCorsConfigurationSource { corsConfiguration }
        return corsConfiguration
    }
}