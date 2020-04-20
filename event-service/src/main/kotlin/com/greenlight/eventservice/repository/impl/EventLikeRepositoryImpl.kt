package com.greenlight.eventservice.repository.impl

import com.greenlight.eventservice.domain.Event
import com.greenlight.eventservice.domain.EventLike
import com.greenlight.eventservice.repository.EventLikeRepository
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.data.r2dbc.core.isEquals
import org.springframework.data.r2dbc.query.Criteria
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
class EventLikeRepositoryImpl(private val databaseClient: DatabaseClient) : EventLikeRepository {

    override fun userHasLikedEvent(userId: String, eventId: Long): Mono<Boolean> {
        val criteria = Criteria.where("event_id").isEquals(eventId)
            .and("user_id").like(userId)

        return databaseClient.select().from(EventLike::class.java)
            .matching(criteria)
            .fetch().first().map { true }
            .switchIfEmpty(Mono.just(false))
    }

    override fun findAllByEvent(event: Event): Flux<EventLike> {
        return findAllByEventId(event.id!!)
    }

    override fun findAllByEventId(id: Long): Flux<EventLike> {
        val criteria = Criteria.where("event_id").isEquals(id)

        return databaseClient.select().from(EventLike::class.java)
            .matching(criteria)
            .fetch().all()
    }

    override fun findAllByUserId(userId: String): Flux<EventLike> {
        val criteria = Criteria.where("user_id").like(userId)

        return databaseClient.select().from(EventLike::class.java)
            .matching(criteria)
            .fetch().all()
    }

    override fun count(id: Long): Mono<Long> {
        return findAllByEventId(id).count()
    }

    override fun save(eventLike: EventLike): Mono<Unit> {
        return databaseClient.insert()
            .into(EventLike::class.java)
            .using(eventLike)
            .fetch().rowsUpdated().map { Unit }
    }


    override fun delete(eventLike: EventLike): Mono<Unit> {
        return delete(eventLike.userId, eventLike.eventId)
    }

    override fun delete(userId: String, eventId: Long): Mono<Unit> {
        val criteria = Criteria.where("event_id").isEquals(eventId)
            .and("user_id").isEquals(userId)

        return databaseClient.delete()
            .from(EventLike::class.java)
            .matching(criteria)
            .fetch().rowsUpdated().map { Unit }
    }
}