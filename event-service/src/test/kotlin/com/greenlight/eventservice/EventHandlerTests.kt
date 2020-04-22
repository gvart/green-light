package com.greenlight.eventservice

import com.greenlight.eventservice.domain.GeoLocation
import com.greenlight.eventservice.extensions.printResponse
import com.greenlight.eventservice.transfer.event.EventRequest
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class EventHandlerTests : AbstractIntegrationTest() {

    private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")

    @Test
    fun createEventTest() {
        val request = EventRequest(
            "title",
            "description",
            GeoLocation(0.0, 0.0, "street"),
            1,
            LocalDateTime.now(),
            10,
            emptySet()
        )

        webClient.post().uri("/api/v1/events").accept(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .exchange()
            .printResponse()
            .expectStatus().isCreated
            .expectBody()
            .jsonPath("$.id").isNotEmpty
            .jsonPath("$.title").isEqualTo(request.title)
            .jsonPath("$.content").isEqualTo(request.content)
            .jsonPath("$.status").isNotEmpty
            .jsonPath("$.type").isNotEmpty
            .jsonPath("$.geoLocation.lat").isEqualTo(request.geoLocation.lat)
            .jsonPath("$.geoLocation.lng").isEqualTo(request.geoLocation.lng)
            .jsonPath("$.geoLocation.street").isEqualTo(request.geoLocation.street)
            .jsonPath("$.createdAt").isNotEmpty
            .jsonPath("$.startsAt").isEqualTo(request.startsAt.format(formatter))
            .jsonPath("$.updatedAt").isEmpty
            .jsonPath("$.finishedAt").isEmpty
            .jsonPath("$.userId").isEqualTo("userId")
            .jsonPath("$.userName").isEqualTo("username")
            .jsonPath("$.peopleRequired").isEqualTo(request.peopleRequired)
            .jsonPath("$.likes").isEqualTo(0)
            .jsonPath("$.items").isEmpty
    }
}