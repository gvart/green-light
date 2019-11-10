package com.greenlight.event.client

import com.greenlight.event.transfer.UserResponse
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange

@Component
class UserServiceClient(webClientBuilder: WebClient.Builder) {
    private val webClient: WebClient = webClientBuilder.baseUrl("http://user-service/api/v1").build()

    suspend fun findUserById(id: Long): UserResponse {
        return webClient.get().uri("/user/$id").accept(MediaType.APPLICATION_JSON)
            .awaitExchange()
            .awaitBody()
    }
}

