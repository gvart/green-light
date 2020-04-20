package com.greenlight.eventservice.service

import com.greenlight.common.security.service.SecurityService
import com.greenlight.common.service.AbstractCoroutineReadWriteService
import com.greenlight.common.web.error.httperror.NotFoundException
import com.greenlight.eventservice.domain.EventParticipant
import com.greenlight.eventservice.repository.EventParticipantRepository
import com.greenlight.eventservice.repository.EventRepository
import com.greenlight.eventservice.transfer.event.EventParticipantRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrElse
import kotlinx.coroutines.reactive.awaitSingle
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.validation.Validator

@Service
class EventParticipantService(
    private val repository: EventParticipantRepository,
    private val eventRepository: EventRepository,
    private val securityService: SecurityService,
    validator: Validator
) : AbstractCoroutineReadWriteService<EventParticipant, EventParticipantRequest, Long>(validator) {

    companion object {
        private val logger = LoggerFactory.getLogger(EventService::class.java)
    }

    suspend fun findAllByEventId(id: Long): Flow<EventParticipant> {
        logger.debug("Request to find all event participants by event id: $id")
        return repository.findAllByEventId(id)
    }

    override suspend fun saveEntity(request: EventParticipantRequest): EventParticipant {
        checkEventExistence(request.eventId)

        val currentUserId = securityService.extractUserId().awaitSingle()
        val username = securityService.extractUsername().awaitSingle()

        val eventParticipant = request.convert().apply {
            userId = currentUserId
            userName = username
        }
        return repository.save(eventParticipant).awaitSingle()
    }

    override suspend fun updateEntity(id: Long, request: EventParticipantRequest): EventParticipant {
        val eventParticipant = findOne(id)
        checkEventExistence(request.eventId)

        val currentUserId = securityService.extractUserId().awaitSingle()
        val username = securityService.extractUsername().awaitSingle()

        eventParticipant.apply {
            eventId = request.eventId
            userId = currentUserId
            userName = username
        }
        return repository.save(eventParticipant).awaitSingle()
    }

    private suspend fun checkEventExistence(id: Long) {
        val eventExists = eventRepository.existsById(id).awaitFirst()
        if (!eventExists) {
            logger.warn("Event $id not found.")
            throw NotFoundException("Requested event not found.")
        }
    }

    override suspend fun deleteEntity(id: Long) {
        repository.deleteById(id)
    }

    override fun findAllEntities(): Flow<EventParticipant> {
        return repository.findAll().asFlow()
    }

    override suspend fun findOneEntity(id: Long): EventParticipant {
        return repository.findById(id)
            .awaitFirstOrElse { throw NotFoundException("Participant $id not found") }
    }

    override fun getLogger(): Logger {
        return logger
    }
}