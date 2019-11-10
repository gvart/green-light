package com.greenlight.event.service

import com.greenlight.event.domain.EventStatus
import com.greenlight.event.error.NotFoundException
import com.greenlight.event.repository.EventStatusRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrElse
import org.springframework.stereotype.Service

@Service
class EventStatusService(private val eventStatusRepository: EventStatusRepository) : ReadOnlyService<EventStatus, Int> {

    override fun findAll(): Flow<EventStatus> {
        return eventStatusRepository.findAll().asFlow()
    }

    override suspend fun findOne(id: Int): EventStatus {
        return eventStatusRepository.findById(id).
            awaitFirstOrElse { throw NotFoundException("Status $id not found") }
    }
}
