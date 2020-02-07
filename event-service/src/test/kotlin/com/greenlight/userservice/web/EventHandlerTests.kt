package com.greenlight.userservice.web

import com.greenlight.eventservice.client.UserServiceClient
import com.greenlight.eventservice.config.RouterConfig
import com.greenlight.eventservice.domain.Event
import com.greenlight.eventservice.domain.EventStatus
import com.greenlight.eventservice.domain.Point
import com.greenlight.userservice.extensions.printResponse
import com.greenlight.eventservice.repository.EventRepository
import com.greenlight.eventservice.service.EventService
import com.greenlight.eventservice.service.EventStatusService
import com.greenlight.eventservice.transfer.EventRequest
import com.greenlight.eventservice.web.EventHandler
import com.greenlight.eventservice.web.EventStatusHandler
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
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
import java.time.format.DateTimeFormatter


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

    private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
    private val statusId = 1L
    private val eventStatus = EventStatus(statusId, "name", true)

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
    fun setup() {
        runBlocking {
            Mockito.`when`(eventStatusService.findOne(statusId)).thenReturn(eventStatus)
            Mockito.`when`(eventRepository.save(any<Event>())).thenAnswer {
                val argument = it.getArgument(0, Event::class.java)
                Mono.just(argument)
            }
        }
    }

    @Test
    fun createEventTest() {
        val request = EventRequest(
            "title",
            "description",
            1L,
            Point(0.0, 0.0),
            LocalDateTime.now(),
            10
        )
        webClient.post().uri("/api/v1/event").accept(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .exchange()
            .printResponse()
            .expectStatus().isCreated
            .expectBody()
            .jsonPath("$.title").isEqualTo(request.title)
            .jsonPath("$.description").isEqualTo(request.description)
            .jsonPath("$.geoLocation.lat").isEqualTo(request.geoLocation.lat)
            .jsonPath("$.geoLocation.lon").isEqualTo(request.geoLocation.lon)
            .jsonPath("$.startsAt").isEqualTo(request.startsAt.format(formatter))
            .jsonPath("$.peopleRequired").isEqualTo(request.peopleRequired)
    }
}