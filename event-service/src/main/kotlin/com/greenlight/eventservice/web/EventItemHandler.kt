package com.greenlight.eventservice.web

import com.greenlight.eventservice.service.EventItemService
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyAndAwait

@Component
class EventItemHandler(private val service: EventItemService) {
    suspend fun findAll(request: ServerRequest): ServerResponse {
        val items = service.findAll()
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyAndAwait(items)
    }
}
