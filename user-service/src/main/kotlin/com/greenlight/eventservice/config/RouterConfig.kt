package com.greenlight.eventservice.config

import com.greenlight.eventservice.web.UserProfileHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.coRouter

@Component
class RouterConfig(private val userProfileHandler: UserProfileHandler) {

    @Bean
    fun router() = coRouter {
        "/api/v1".nest {
            "/user".nest {
                GET("", userProfileHandler::findAll)
                POST("", userProfileHandler::save)
                "/{id}".nest {
                    GET("", userProfileHandler::findOne)
                    DELETE("", userProfileHandler::delete)
                    PUT("", userProfileHandler::update)
                }
            }
        }
    }
}