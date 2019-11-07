package com.greenlight.event.service

import com.greenlight.event.domain.Event
import com.greenlight.event.error.NotFoundException
import com.greenlight.event.repository.EventRepository
import com.greenlight.event.repository.EventStatusRepository
import com.greenlight.event.transfer.EventRequest
import com.greenlight.event.validation.ValidationUtil
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class EventService(
    private val eventRepository: EventRepository,
    private val eventStatusRepository: EventStatusRepository,
    private val validator: ValidationUtil
) : ReadWriteService<Event, EventRequest, Long> {
    override fun findAll(): Flux<Event> {
        return eventRepository.findAll()
    }

    override fun findOne(id: Long): Mono<Event> = eventRepository.findById(id)

    override fun save(entity: Mono<EventRequest>): Mono<Event> =
        entity.flatMap { validator.validate(it) }.flatMap { body ->
            eventStatusRepository
                .findById(body.statusId)
                .switchIfEmpty(Mono.error { NotFoundException("Not found event status with id: ${body.statusId}") })
                .flatMap { eventStatus ->
                    val event = body.convert().apply {
                        authorId = 2
                        status = eventStatus
                    }
                    eventRepository.save(event)
                }
        }

    override fun update(id: Long, entity: Mono<EventRequest>): Mono<Event> = Mono.empty()
//        eventRepository.findById(id)
//            .switchIfEmpty(Mono.error { NotFoundException("Not found event with id: $id") })
//            .flatMap { event ->
//                event.updatedAt = LocalDateTime.now()
//                event.title =
//            }

    override fun delete(id: Long): Mono<Void> = eventRepository.deleteById(id)
}
