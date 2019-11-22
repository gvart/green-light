package com.greenlight.userservice.config

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.coRouter

@Component
class RouterConfig() {

    @Bean
    fun router() = coRouter {
        "/api/v1".nest {
            "/user-service".nest {
            }
        }
    }
}