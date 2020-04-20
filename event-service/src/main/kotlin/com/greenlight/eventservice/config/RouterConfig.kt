package com.greenlight.eventservice.config

import com.greenlight.eventservice.web.EventHandler
import com.greenlight.eventservice.web.EventItemHandler
import com.greenlight.eventservice.web.EventParticipantHandler
import com.greenlight.eventservice.web.EventStatusHandler
import com.greenlight.eventservice.web.EventTypeHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.coRouter

@Component
class RouterConfig(
    private val eventStatusHandler: EventStatusHandler,
    private val eventHandler: EventHandler,
    private val eventTypeHandler: EventTypeHandler,
    private val eventItemHandler: EventItemHandler,
    private val eventParticipantHandler: EventParticipantHandler
) {

    @Bean
    fun router() = coRouter {
        "/api/v1".nest {
            "/statuses".nest {
                GET("", eventStatusHandler::findAll)
            }
            "/items".nest {
                GET("", eventItemHandler::findAll)
            }
            "/events".nest {
                GET("", eventHandler::findAll)
                POST("", eventHandler::save)
                "/{id}".nest {
                    PUT("/like", eventHandler::like)
                    GET("", eventHandler::findOne)
                    DELETE("", eventHandler::delete)
                    PUT("", eventHandler::update)
                }
            }
            "/types".nest {
                GET("", eventTypeHandler::findAll)
                POST("", eventTypeHandler::save)
                "/{id}".nest {
                    GET("", eventTypeHandler::findOne)
                    DELETE("", eventTypeHandler::delete)
                    PUT("", eventTypeHandler::update)
                }
            }
            "/participants".nest {
                GET("", eventParticipantHandler::findAll)
                POST("", eventParticipantHandler::save)
                "/{id}".nest {
                    DELETE("", eventTypeHandler::delete)
                }
            }
        }
    }
}