package com.greenlight.eventservice.service

import com.greenlight.common.security.service.SecurityService
import com.greenlight.common.web.error.httperror.NotFoundException
import com.greenlight.common.service.AbstractCoroutineReadWriteService
import com.greenlight.eventservice.domain.Event
import com.greenlight.eventservice.repository.EventRepository
import com.greenlight.eventservice.transfer.EventRequest
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
class EventService(
    private val eventRepository: EventRepository,
    private val eventStatusService: EventStatusService,
    private val securityService: SecurityService,
    validator: Validator
) : AbstractCoroutineReadWriteService<Event, EventRequest, Long>(validator) {

    companion object {
        private val logger = LoggerFactory.getLogger(EventService::class.java)
    }

    override fun findAllEntities(): Flow<Event> {
        return eventRepository.findAll().asFlow()
    }

    override suspend fun findOneEntity(id: Long): Event = eventRepository.findById(id)
        .awaitFirstOrElse { throw NotFoundException("Event $id not found") }


    override suspend fun saveEntity(request: EventRequest): Event {
        val eventStatus = eventStatusService.findOne(request.statusId)
        val userId = securityService.extractUserId().awaitFirst()
        val event = request.convert().apply {
            authorId = userId
            status = eventStatus.id
        }

        return eventRepository.save(event).awaitSingle()
    }


    override suspend fun updateEntity(id: Long, request: EventRequest): Event {
        val eventStatus = eventStatusService.findOne(request.statusId)
        val event = findOne(id)

        event.apply {
            title = request.title
            description = request.description
            geoLocation = request.geoLocation
            peopleRequired = request.peopleRequired
            status = eventStatus.id
        }

        return eventRepository.save(event).awaitSingle()
    }

    override suspend fun deleteEntity(id: Long) {
        eventRepository.deleteById(id).awaitSingle()
    }

    override fun getLogger(): Logger {
        return logger
    }

}
