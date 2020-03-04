package com.greenlight.eventservice.service

import com.greenlight.common.web.error.httperror.NotFoundException
import com.greenlight.common.service.AbstractCoroutineReadOnlyService
import com.greenlight.eventservice.domain.EventStatus
import com.greenlight.eventservice.repository.EventStatusRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrElse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class EventStatusService(private val eventStatusRepository: EventStatusRepository) :
    AbstractCoroutineReadOnlyService<EventStatus, Long>() {

    companion object {
        private val logger = LoggerFactory.getLogger(EventStatusService::class.java)
    }

    override fun findAllEntities(): Flow<EventStatus> {
        return eventStatusRepository.findAll().asFlow()
    }

    override suspend fun findOneEntity(id: Long): EventStatus {
        return eventStatusRepository.findById(id).awaitFirstOrElse { throw NotFoundException("Status $id not found") }
    }

    override fun getLogger(): Logger {
        return logger
    }
}
