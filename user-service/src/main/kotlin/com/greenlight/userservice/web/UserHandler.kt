package com.greenlight.userservice.web

import com.greenlight.common.web.handler.CRUDHandler
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Component
class UserHandler : CRUDHandler {
    override suspend fun save(request: ServerRequest): ServerResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun update(request: ServerRequest): ServerResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun findAll(request: ServerRequest): ServerResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun findOne(request: ServerRequest): ServerResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun delete(request: ServerRequest): ServerResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}