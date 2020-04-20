package com.greenlight.eventservice.repository

import com.greenlight.eventservice.domain.Event
import com.greenlight.eventservice.domain.EventLike
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface EventLikeRepository {
    fun userHasLikedEvent(userId: String, eventId: Long): Mono<Boolean>
    fun findAllByEvent(event: Event): Flux<EventLike>
    fun findAllByEventId(id: Long): Flux<EventLike>
    fun findAllByUserId(userId: String): Flux<EventLike>
    fun count(id: Long): Mono<Long>
    fun save(eventLike: EventLike): Mono<Unit>
    fun delete(eventLike: EventLike): Mono<Unit>
    fun delete(userId: String, eventId: Long): Mono<Unit>
}