package com.greenlight.feedbackservice.config

import com.greenlight.feedbackservice.web.CommentHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.coRouter

@Component
class RouterConfig(
    private val commentHandler: CommentHandler
) {

    @Bean
    fun router() = coRouter {
        "/api/v1".nest {
            "/comments".nest {
                GET("", commentHandler::findAll)
                POST("", commentHandler::save)
                "/{id}".nest {
                    GET("", commentHandler::findOne)
                    DELETE("", commentHandler::delete)
                    PUT("", commentHandler::update)
                }
            }
        }
    }
}
