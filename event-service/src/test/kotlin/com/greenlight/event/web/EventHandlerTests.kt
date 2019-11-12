package com.greenlight.event.web

import com.greenlight.event.client.UserServiceClient
import com.greenlight.event.config.RouterConfig
import com.greenlight.event.domain.Event
import com.greenlight.event.domain.EventStatus
import com.greenlight.event.domain.Point
import com.greenlight.event.extensions.printResponse
import com.greenlight.event.repository.EventRepository
import com.greenlight.event.service.EventService
import com.greenlight.event.service.EventStatusService
import com.greenlight.event.transfer.EventRequest
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
                Mono.just(it.getArgument(0, Event::class.java))
            }
        }
    }

    @Test
    fun createEventTest() {
        val request = EventRequest("title", "description", 1, Point(0.0, 0.0), LocalDateTime.now(), 10)
        webClient.post().uri("/api/v1/event").accept(MediaType.APPLICATION_JSON)
            .bodyValue(request)
            .exchange()
            .printResponse()
            .expectStatus().isCreated
            .expectBody()
//            .jsonPath("$.id").exists()
            .jsonPath("$.title").isEqualTo(request.title)
            .jsonPath("$.description").isEqualTo(request.description)
//            .jsonPath("$.geoLocation").isEqualTo(request.geoLocation)
            .jsonPath("$.startsAt").isEqualTo(request.startsAt)
            .jsonPath("$.peopleRequired").isEqualTo(request.peopleRequired)


    }
}