package com.greenlight.userservice.initializer

import com.greenlight.userservice.domain.EventStatus
import com.greenlight.userservice.repository.EventStatusRepository
import com.greenlight.userservice.service.EventService
import com.greenlight.userservice.transfer.EventRequest
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.runBlocking
import org.springframework.boot.CommandLineRunner
import org.springframework.data.geo.Point
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class StatusInitializer(
    private val eventStatusRepository: EventStatusRepository,
    private val eventService: EventService
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        runBlocking {
            val size = eventStatusRepository.count().awaitFirst()
            if (size.toInt() == 0) {
                eventStatusRepository.saveAll(
                    listOf(
                        EventStatus(null, "CREATED", true),
                        EventStatus(null, "IN_PROGRESS", true),
                        EventStatus(null, "FINISHED", true)
                    )
                ).subscribe()
            }

            eventService.save(
                EventRequest(
                    "test", "description", eventStatusRepository.findAll().awaitFirst().id!!, Point(
                        10.0,
                        10.0
                    ), LocalDateTime.now(), 10
                )
            )
        }
    }
}