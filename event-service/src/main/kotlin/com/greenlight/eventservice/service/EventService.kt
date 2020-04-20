package com.greenlight.eventservice.service

import com.greenlight.common.security.service.SecurityService
import com.greenlight.common.service.AbstractCoroutineReadWriteService
import com.greenlight.common.web.error.httperror.NotFoundException
import com.greenlight.eventservice.domain.Event
import com.greenlight.eventservice.repository.DefaultEventType
import com.greenlight.eventservice.repository.EventRepository
import com.greenlight.eventservice.transfer.event.EventDetailsResponse
import com.greenlight.eventservice.transfer.event.EventRequest
import com.greenlight.eventservice.transfer.event.EventShortDetailsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrElse
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactor.asFlux
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.validation.Validator

@Service
class EventService(
    private val eventRepository: EventRepository,
    private val eventStatusService: EventStatusService,
    private val eventTypeService: EventTypeService,
    private val securityService: SecurityService,
    private val eventLikeService: EventLikeService,
    validator: Validator
) : AbstractCoroutineReadWriteService<Event, EventRequest, Long>(validator) {

    companion object {
        private val logger = LoggerFactory.getLogger(EventService::class.java)
    }

    //    TODO find optimal solution at database schema
    suspend fun findAllShort(): Flow<EventShortDetailsResponse> {
        val eventTypes = eventTypeService.findAll().asFlux().collectList().awaitFirst()
        val eventStatuses = eventStatusService.findAll().asFlux().collectList().awaitFirst()
        val allLikesCurrentUser = eventLikeService.findAllCurrentUser()
        eventLikeService
        return eventRepository.findAll().map { event ->
            EventShortDetailsResponse(
                event,
                eventStatuses.find { it.id == event.status }!!,
                eventTypes.find { it.id == event.type }!!,
                allLikesCurrentUser.any { it.eventId == event.id }
            )
        }.asFlow()
    }

    override fun findAllEntities(): Flow<Event> {
        return eventRepository.findAll().asFlow()
    }

    override suspend fun findOneEntity(id: Long): Event = eventRepository.findById(id)
        .awaitFirstOrElse { throw NotFoundException("Event $id not found") }


    override suspend fun saveEntity(request: EventRequest): Event {
        val userId = securityService.extractUserId().awaitFirst()
        val username = securityService.extractUsername().awaitFirst()

        val event = request.convert().apply {
            this.userId = userId
            userName = username
            status = DefaultEventType.CREATED.id
        }

        return eventRepository.save(event).awaitSingle()
    }


    override suspend fun updateEntity(id: Long, request: EventRequest): Event {
        val event = findOne(id)
        val eventType = eventTypeService.findOne(request.typeId)

        event.apply {
            title = request.title
            content = request.content
            geoLocation = request.geoLocation
            peopleRequired = request.peopleRequired
            type = eventType.id
        }

        return eventRepository.save(event).awaitSingle()
    }

    override suspend fun deleteEntity(id: Long) {
        eventRepository.deleteById(id).awaitSingle()
    }

    suspend fun findOneDetails(id: Long): EventDetailsResponse {
        getLogger().debug("Request to find event details for $id id.")
        val event = findOne(id)
        val status = eventStatusService.findOne(event.status!!)
        val type = eventTypeService.findOne(event.type!!)
        val numberOfLikes = eventLikeService.count(event.id!!)
        val currentUserHasLike = eventLikeService.currentUserHasLike(event.id)
        return EventDetailsResponse(event, status, type, 0, numberOfLikes, currentUserHasLike)

    }

    suspend fun changeNumberOfLikes(id: Long, numberOfLikes: Int) {
        getLogger().debug("Request to update number of likes for event $id by $numberOfLikes.")
        val event = findOne(id)
        event.likes += numberOfLikes
        eventRepository.save(event).awaitSingle()
    }

    override fun getLogger(): Logger {
        return logger
    }

}
