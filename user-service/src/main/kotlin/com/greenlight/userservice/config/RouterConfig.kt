package com.greenlight.userservice.config

import com.greenlight.userservice.web.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.coRouter

@Component
class RouterConfig(private val userHandler: UserHandler) {

    @Bean
    fun router() = coRouter {
        "/api/v1".nest {
            "/user".nest {
                GET("", userHandler::findAll)
                POST("", userHandler::save)
                "/{id}".nest {
                    GET("", userHandler::findOne)
                    DELETE("", userHandler::delete)
                    PUT("", userHandler::update)
                }
            }
        }
    }
}