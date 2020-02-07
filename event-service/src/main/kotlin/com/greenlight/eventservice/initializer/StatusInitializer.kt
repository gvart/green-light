package com.greenlight.eventservice.initializer

import com.greenlight.eventservice.domain.EventStatus
import com.greenlight.eventservice.repository.EventStatusRepository
import com.greenlight.eventservice.service.EventService
import com.greenlight.eventservice.transfer.EventRequest
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.runBlocking
import org.springframework.boot.CommandLineRunner
import com.greenlight.eventservice.domain.Point
import org.springframework.stereotype.Component
import java.time.LocalDateTime

//@Component
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
                    "test",
                    "description",
                    eventStatusRepository.findAll().awaitFirst().id!!,
                    Point(
                        10.0,
                        10.0
                    ),
                    LocalDateTime.now(),
                    10
                )
            )
        }
    }
}