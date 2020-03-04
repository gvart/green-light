package com.greenlight.userservice.reactive.service

import com.greenlight.userservice.transfer.user.UserAuthRequest
import reactor.core.publisher.Mono


interface AuthServiceClient {

    fun createUserAccount(username: UserAuthRequest): Mono<Unit>
}