package com.greenlight.event.web

import com.greenlight.event.client.UserServiceClient
import com.greenlight.event.config.RouterConfig
import com.greenlight.event.domain.Event
import com.greenlight.event.domain.EventStatus
import com.greenlight.event.domain.Point
import com.greenlight.event.repository.EventRepository
import com.greenlight.event.service.EventService
import com.greenlight.event.service.EventStatusService
import com.greenlight.event.transfer.EventRequest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyObject
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono
import java.time.LocalDateTime


@WebFluxTest
@ContextConfiguration(
    classes = [
        RouterConfig::class,
        EventHandler::class,
        EventService::class
    ]
)
@ExtendWith(SpringExtension::class)
class EventHandlerTests {

    private val statusId = 1
    private val eventStatus = EventStatus(statusId, "name", true)
    private val event = Event(
        1,
        "title",
        "description",
        eventStatus,
        Point(10.0, 10.0),
        LocalDateTime.now(),
        LocalDateTime.now(),
        null,
        null,
        1,
        10
    )

    @Autowired
    private lateinit var webClient: WebTestClient

    @MockBean
    private lateinit var eventStatusHandler: EventStatusHandler

    @MockBean
    private lateinit var eventStatusService: EventStatusService

    @MockBean
    private lateinit var userServiceClient: UserServiceClient

    @MockBean
    private lateinit var eventRepository: EventRepository

    @BeforeEach
    suspend fun setup() {
        Mockito.`when`(eventStatusService.findOne(statusId)).thenReturn(eventStatus)
        Mockito.`when`(eventRepository.save(anyObject<Event>())).thenReturn(
            Mono.just(event)
        )
    }

    @Test
    fun createEventTest() {
        val request = EventRequest("title", "description", 1, Point(0.0, 0.0), LocalDateTime.now(), 10)
        webClient.post().uri("/api/v1/event").accept(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .exchange()
            .expectStatus().isCreated
            .expectBody()
    }
}