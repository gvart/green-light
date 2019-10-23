package com.greenlight.event.config

import com.greenlight.event.web.EventStatusHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class RouterConfig(private val eventStatusHandler: EventStatusHandler) {

    @Bean
    fun router() = router {
        "/api/v1".nest {
            "/event-status".nest {
                GET("", eventStatusHandler::findAll)
            }
        }
    }
}