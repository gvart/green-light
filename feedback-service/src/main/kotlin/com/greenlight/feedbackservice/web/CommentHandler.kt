package com.greenlight.feedbackservice.web

import com.greenlight.common.web.error.httperror.MissingQueryParamException
import com.greenlight.common.web.handler.AbstractCRUDHandler
import com.greenlight.feedbackservice.domain.Comment
import com.greenlight.feedbackservice.service.CommentService
import com.greenlight.feedbackservice.transfer.CommentRequest
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody

@Component
class CommentHandler(private val service: CommentService) :
    AbstractCRUDHandler<Comment, CommentRequest, Long>(service) {

    override suspend fun extractBody(request: ServerRequest): CommentRequest {
        return request.awaitBody()
    }

    override suspend fun extractId(request: ServerRequest): Long {
        return request.pathVariable("id").toLong()
    }

    override suspend fun findAll(request: ServerRequest): ServerResponse {
        val queryParam = request.queryParam("eventId")
        if (!queryParam.isPresent) {
            throw MissingQueryParamException("eventId")
        }

        val comments = service.findAllByEventId(queryParam.get().toLong())
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(comments, Comment::class.java).awaitSingle()
    }

}