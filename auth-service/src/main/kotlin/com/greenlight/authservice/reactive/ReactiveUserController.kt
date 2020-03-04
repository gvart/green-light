package com.greenlight.authservice.reactive

import com.greenlight.authservice.service.user.UserService
import com.greenlight.authservice.tranfser.CreateUserRequest
import com.greenlight.common.web.error.httperror.HttpException
import org.springframework.messaging.handler.annotation.MessageExceptionHandler
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Mono

@Controller
class ReactiveUserController(
    private val userService: UserService
) {

    @MessageMapping("user/create/regular")
    fun createUser(request: CreateUserRequest): Mono<Unit> {
        userService.save(request)
        return Mono.empty()
    }


    @MessageExceptionHandler
    private fun handleException(exception: Exception): Mono<Unit> {
        if (exception is HttpException) {

            return Mono.error(exception)
        }
        return Mono.error(exception)
    }
}