package com.greenlight.eventservice.web

import com.greenlight.common.web.handler.AbstractCRUDHandler
import com.greenlight.eventservice.domain.UserProfile
import com.greenlight.eventservice.service.UserProfileService
import com.greenlight.eventservice.transfer.user.UserProfileRequest
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.awaitBody

@Component
class UserProfileHandler(userService: UserProfileService) : AbstractCRUDHandler<UserProfile, UserProfileRequest, Long>(userService) {

    override suspend fun extractBody(request: ServerRequest): UserProfileRequest {
        return request.awaitBody()
    }

    override suspend fun extractId(request: ServerRequest): Long {
        return request.pathVariable("id").toLong()
    }

}