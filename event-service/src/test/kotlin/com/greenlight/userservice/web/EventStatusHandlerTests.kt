package com.greenlight.userservice.web

import com.greenlight.userservice.config.RouterConfig
import com.greenlight.userservice.domain.EventStatus
import com.greenlight.userservice.repository.EventStatusRepository
import com.greenlight.userservice.service.EventStatusService
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
    private lateinit var eventHandler: EventHandler

    @MockBean
    private lateinit var eventStatusRepository: EventStatusRepository

    private val events = arrayOf(
        EventStatus("id1", "status1", false),
        EventStatus("id2", "status2", false),
        EventStatus("id3", "status3", false)
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
            .jsonPath("$[0].id").exists()
            .jsonPath("$[0].name").exists()
            .jsonPath("$[0].allowActions").isBoolean
    }
}