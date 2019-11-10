package com.greenlight.event.web

import com.greenlight.event.service.EventStatusService
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyAndAwait

@Component
class EventStatusHandler(private val service: EventStatusService) {
    suspend fun findAll(request: ServerRequest): ServerResponse {
        val statuses = service.findAll()
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyAndAwait(statuses)
    }
}
