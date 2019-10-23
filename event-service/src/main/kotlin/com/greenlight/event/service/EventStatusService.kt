package com.greenlight.event.service

import com.greenlight.event.domain.EventStatus
import com.greenlight.event.repository.EventStatusRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class EventStatusService(private val eventStatusRepository: EventStatusRepository) : ReadOnlyService<EventStatus, Int> {

    override fun findAll(): Flux<EventStatus> {
        return eventStatusRepository.findAll()
    }

    override fun findOne(id: Int): Mono<EventStatus> {
        return eventStatusRepository.findById(id)
    }
}
