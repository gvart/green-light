package com.greenlight.event.web

import com.greenlight.event.config.RouterConfig
import com.greenlight.event.domain.EventStatus
import com.greenlight.event.repository.EventStatusRepository
import com.greenlight.event.service.EventStatusService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Flux


@WebFluxTest
@ContextConfiguration(
    classes = [
        RouterConfig::class,
        EventStatusHandler::class,
        EventStatusService::class,
        EventStatusRepository::class
    ]
)
@ExtendWith(SpringExtension::class)
class EventStatusHandlerTests {

    @Autowired
    private lateinit var webClient: WebTestClient

    @MockBean
    private lateinit var eventStatusRepository: EventStatusRepository

    private val events = arrayOf(
        EventStatus(1, "status1", false),
        EventStatus(2, "status2", false),
        EventStatus(3, "status3", false)
    )

    @BeforeEach
    fun setup() {
        Mockito.`when`(eventStatusRepository.findAll()).thenReturn(Flux.just(*events))
    }

    @Test
    fun getAllStatusesTest() {
        webClient.get().uri("/api/v1/event-status").accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$").isArray
            .jsonPath("$[0].id").isNumber
            .jsonPath("$[0].name").exists()
            .jsonPath("$[0].allowActions").isBoolean
    }
}