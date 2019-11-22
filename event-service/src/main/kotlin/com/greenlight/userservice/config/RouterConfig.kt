package com.greenlight.userservice.config

import com.greenlight.userservice.web.EventHandler
import com.greenlight.userservice.web.EventStatusHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.coRouter

@Component
class RouterConfig(
    private val eventStatusHandler: EventStatusHandler,
    private val eventHandler: EventHandler
) {

    @Bean
    fun router() = coRouter {
        "/api/v1".nest {
            "/event-status".nest {
                GET("", eventStatusHandler::findAll)
            }
            "/event".nest {
                GET("", eventHandler::findAll)
                POST("", eventHandler::save)
                "/{id}".nest {
                    GET("", eventHandler::findOne)
                    DELETE("", eventHandler::delete)
                    PUT("", eventHandler::update)

                }

            }
        }
    }
}