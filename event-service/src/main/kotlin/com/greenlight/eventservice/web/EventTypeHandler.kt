package com.greenlight.eventservice.web

import com.greenlight.common.web.handler.AbstractCRUDHandler
import com.greenlight.eventservice.domain.EventType
import com.greenlight.eventservice.service.EventTypeService
import com.greenlight.eventservice.transfer.EventTypeRequest
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.awaitBody

@Component
class EventTypeHandler(service: EventTypeService) : AbstractCRUDHandler<EventType, EventTypeRequest, Int>(service) {

    override suspend fun extractBody(request: ServerRequest): EventTypeRequest {
        return request.awaitBody()
    }

    override suspend fun extractId(request: ServerRequest): Int {
        return request.pathVariable("id").toInt()
    }
}
