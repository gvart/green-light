package com.greenlight.eventservice.web

import com.greenlight.common.web.handler.AbstractCRUDHandler
import com.greenlight.common.web.helper.QueryParameterHandler
import com.greenlight.eventservice.domain.Event
import com.greenlight.eventservice.service.EventLikeService
import com.greenlight.eventservice.service.EventService
import com.greenlight.eventservice.transfer.event.EventRequest
import com.greenlight.eventservice.transfer.event.EventShortDetailsResponse
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactor.asFlux
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class EventHandler(private val service: EventService, private val eventLikeService: EventLikeService) :
    AbstractCRUDHandler<Event, EventRequest, Long>(service) {

    override suspend fun extractBody(request: ServerRequest): EventRequest {
        return request.awaitBody()
    }

    override suspend fun extractId(request: ServerRequest): Long {
        return request.pathVariable("id").toLong()
    }

    override suspend fun findAll(request: ServerRequest): ServerResponse {

        return QueryParameterHandler.handle(
            "type",
            request,
            QueryParameterHandler.QueryMappedValue("short", suspend { findAllShort() }),
            QueryParameterHandler.QueryMappedValue("long", suspend { super.findAll(request) })
        )
    }

    override suspend fun findOne(request: ServerRequest): ServerResponse {
        val id = extractId(request)
        val foundEntity = service.findOneDetails(id)
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(foundEntity)
    }

    private suspend fun findAllShort(): ServerResponse {
        val bodyFromPublisher = BodyInserters.fromPublisher(service.findAllShort().asFlux(),
            object : ParameterizedTypeReference<EventShortDetailsResponse>() {})
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON).body(bodyFromPublisher).awaitFirst()
    }

    suspend fun like(request: ServerRequest): ServerResponse {
        val id = extractId(request)
        val numberOfLikes = eventLikeService.triggerLike(id)
        service.changeNumberOfLikes(id, numberOfLikes);
        return ServerResponse.ok().build().awaitSingle()
    }
}
