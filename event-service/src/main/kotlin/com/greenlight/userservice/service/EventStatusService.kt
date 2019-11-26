package com.greenlight.userservice.service

import com.greenlight.common.web.error.httperror.NotFoundException
import com.greenlight.userservice.domain.EventStatus
import com.greenlight.userservice.repository.EventStatusRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrElse
import org.springframework.stereotype.Service

@Service
class EventStatusService(private val eventStatusRepository: EventStatusRepository) : ReadOnlyService<EventStatus, String> {

    override fun findAll(): Flow<EventStatus> {
        return eventStatusRepository.findAll().asFlow()
    }

    override suspend fun findOne(id: String): EventStatus {
        return eventStatusRepository.findById(id).
            awaitFirstOrElse { throw NotFoundException("Status $id not found") }
    }
}
