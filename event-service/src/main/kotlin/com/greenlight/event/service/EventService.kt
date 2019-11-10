package com.greenlight.event.service

import com.greenlight.event.client.UserServiceClient
import com.greenlight.event.domain.Event
import com.greenlight.event.error.NotFoundException
import com.greenlight.event.error.ValidationException
import com.greenlight.event.repository.EventRepository
import com.greenlight.event.transfer.EventRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrElse
import kotlinx.coroutines.reactive.awaitSingle
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import javax.validation.Validator

@Service
class EventService(
    private val eventRepository: EventRepository,
    private val eventStatusService: EventStatusService,
    private val userServiceClient: UserServiceClient,
    private val validator: Validator
) : ReadWriteService<Event, EventRequest, Long> {

    companion object {
        private val log = LoggerFactory.getLogger(EventService::class.java)
    }

    override fun findAll(): Flow<Event> {
        return eventRepository.findAll().asFlow()
    }

    override suspend fun findOne(id: Long): Event = eventRepository.findById(id)
        .awaitFirstOrElse { throw NotFoundException("Event $id not found") }


    override suspend fun save(request: EventRequest): Event {
        validateEventRequest(request)

        val eventStatus = eventStatusService.findOne(request.statusId)

        //todo get userid from token
        userServiceClient.findUserById(1)
        val event = request.convert().apply {
            authorId = 1
            status = eventStatus
        }

        return eventRepository.save(event).awaitSingle()
    }

    override suspend fun update(id: Long, request: EventRequest): Event {
        validateEventRequest(request)

        val eventStatus = eventStatusService.findOne(request.statusId)
        val event = findOne(id)

        event.apply {
            title = request.title
            description = request.description
            geoLocation = request.geoLocation
            peopleRequired = request.peopleRequired
            status = eventStatus
        }

        return eventRepository.save(event).awaitSingle()
    }

    override suspend fun delete(id: Long): Void = eventRepository.deleteById(id).awaitSingle()


    private fun validateEventRequest(eventRequest: EventRequest) {
        val validationResult = validator.validate(eventRequest)
        if (validationResult.isNotEmpty()) {
            log.error("Errors present")
            throw ValidationException("Invalid object", validationResult)
        }
    }
}
