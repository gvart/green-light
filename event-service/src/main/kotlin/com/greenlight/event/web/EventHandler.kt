package com.greenlight.event.web

import com.greenlight.event.service.EventService
import com.greenlight.event.transfer.EventRequest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class EventHandler(private val service: EventService): CRUDHandler {

    override suspend fun findOne(request: ServerRequest): ServerResponse {
        val eventId = request.pathVariable("id").toLong()
        val event = service.findOne(eventId)
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(event)
    }

    override suspend fun findAll(request: ServerRequest): ServerResponse {
        val events = service.findAll()
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyAndAwait(events)
    }

    override suspend fun save(request: ServerRequest): ServerResponse {
        val eventRequest = request.awaitBody<EventRequest>()
        val event = service.save(eventRequest)
        return ServerResponse.status(HttpStatus.CREATED)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(event)
    }

    override suspend fun update(request: ServerRequest): ServerResponse {
        val eventId = request.pathVariable("id").toLong()
        val eventRequest = request.awaitBody<EventRequest>()
        val event = service.update(eventId, eventRequest)
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(event)
    }

    override suspend fun delete(request: ServerRequest): ServerResponse {
        val eventId = request.pathVariable("id").toLong()
        service.delete(eventId)
        return ServerResponse.status(HttpStatus.NO_CONTENT).buildAndAwait()
    }
}
