package com.greenlight.event.client

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class UserServiceClient(webClientBuilder: WebClient.Builder) {
    var webClient: WebClient = webClientBuilder.baseUrl("http://user-service/api/v1").build()
}

