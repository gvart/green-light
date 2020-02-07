package com.greenlight.eventservice.service

import com.greenlight.common.web.error.httperror.NotFoundException
import com.greenlight.eventservice.domain.EventStatus
import com.greenlight.eventservice.repository.EventStatusRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrElse
import org.springframework.stereotype.Service

@Service
class EventStatusService(private val eventStatusRepository: EventStatusRepository) :
    ReadOnlyService<EventStatus, Long> {

    override fun findAll(): Flow<EventStatus> {
        return eventStatusRepository.findAll().asFlow()
    }

    override suspend fun findOne(id: Long): EventStatus {
        return eventStatusRepository.findById(id).
            awaitFirstOrElse { throw NotFoundException("Status $id not found") }
    }
}
