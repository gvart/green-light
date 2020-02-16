package com.greenlight.userservice.web

import com.greenlight.common.web.handler.CRUDHandler
import com.greenlight.userservice.domain.User
import com.greenlight.userservice.service.UserService
import com.greenlight.userservice.transfer.UserRequest
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.awaitBody

@Component
class UserHandler(userService: UserService) : CRUDHandler<User, UserRequest, Long>(userService) {

    override suspend fun extractBody(request: ServerRequest): UserRequest {
        return request.awaitBody()
    }

    override suspend fun extractId(request: ServerRequest): Long {
        return request.pathVariable("id").toLong()
    }

}