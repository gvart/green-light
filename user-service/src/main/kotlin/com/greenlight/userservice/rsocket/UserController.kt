package com.greenlight.userservice.rsocket

import com.greenlight.userservice.service.UserService
import com.greenlight.userservice.transfer.IdRequest
import com.greenlight.userservice.transfer.IdResponse
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller

@Controller
class UserController(private val userService: UserService) {

    @MessageMapping("user/find")
    suspend fun findById(request: IdRequest): IdResponse {
        return IdResponse(userService.findOne(request.id).id!!)

    }
}