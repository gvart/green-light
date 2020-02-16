package com.greenlight.eventservice.web

import com.greenlight.common.web.handler.CRUDHandler
import com.greenlight.eventservice.domain.Event
import com.greenlight.eventservice.service.EventService
import com.greenlight.eventservice.transfer.EventRequest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyAndAwait
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait

@Component
class EventHandler(service: EventService) : CRUDHandler<Event, EventRequest, Long>(service) {

    override suspend fun extractBody(request: ServerRequest): EventRequest {
        return request.awaitBody()
    }

    override suspend fun extractId(request: ServerRequest): Long {
        return request.pathVariable("id").toLong()
    }
}
