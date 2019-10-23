package com.greenlight.event.web

import com.greenlight.event.domain.EventStatus
import com.greenlight.event.service.EventStatusService
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class EventStatusHandler(private val service: EventStatusService) {
    fun findAll(request: ServerRequest): Mono<ServerResponse> {
        val statuses = service.findAll()
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(statuses, EventStatus::class.java)
    }
}
