package com.greenlight.eventservice.client

import com.greenlight.eventservice.config.RSocketConfig.Companion.USER_SERVICE_CLIENT_BEAN_ID
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class UserServiceClient(
    @Qualifier(USER_SERVICE_CLIENT_BEAN_ID) private val userServiceClient: Mono<RSocketRequester>
) {

}
