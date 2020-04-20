package com.greenlight.eventservice.web

import com.greenlight.common.web.handler.AbstractCRUDHandler
import com.greenlight.eventservice.domain.EventParticipant
import com.greenlight.eventservice.service.EventParticipantService
import com.greenlight.eventservice.transfer.event.EventParticipantRequest
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody

@Component
class EventParticipantHandler(private val service: EventParticipantService) :
    AbstractCRUDHandler<EventParticipant, EventParticipantRequest, Long>(service) {

    override suspend fun extractBody(request: ServerRequest): EventParticipantRequest {
        return request.awaitBody()
    }

    override suspend fun extractId(request: ServerRequest): Long {
        return request.pathVariable("id").toLong()
    }

    override suspend fun findAll(request: ServerRequest): ServerResponse {
        val eventId = request.pathVariable("eventId").toLong()
        val body = BodyInserters.fromProducer(service.findAllByEventId(eventId), EventParticipant::class.java)
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(body)
            .awaitSingle()
    }
}