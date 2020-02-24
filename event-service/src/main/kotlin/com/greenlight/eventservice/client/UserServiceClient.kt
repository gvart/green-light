package com.greenlight.eventservice.client

import com.greenlight.eventservice.config.RSocketConfig.Companion.USER_SERVICE_CLIENT_BEAN_ID
import com.greenlight.eventservice.transfer.IdRequest
import com.greenlight.eventservice.transfer.IdResponse
import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class UserServiceClient(
    @Qualifier(USER_SERVICE_CLIENT_BEAN_ID) private val userServiceClient: Mono<RSocketRequester>
) {


    fun findUserById(id: Long): Mono<IdResponse> {
        return userServiceClient.flatMap {
            it.route("user/find").data(IdRequest(id)).retrieveMono(IdResponse::class.java)
        }
    }
}
