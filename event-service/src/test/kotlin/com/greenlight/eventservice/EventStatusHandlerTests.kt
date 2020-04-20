package com.greenlight.eventservice

import org.junit.jupiter.api.Test
import org.springframework.http.MediaType


class EventStatusHandlerTests : AbstractIntegrationTest() {


    @Test
    fun getAllStatusesTest() {
        webClient.get().uri("/api/v1/statuses").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$").isArray
            .jsonPath("$[0].id").exists()
            .jsonPath("$[0].name").exists()
            .jsonPath("$[0].allowActions").isBoolean
    }
}