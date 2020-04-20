package com.greenlight.eventservice.service

import com.greenlight.common.security.service.SecurityService
import com.greenlight.eventservice.domain.EventLike
import com.greenlight.eventservice.repository.EventLikeRepository
import kotlinx.coroutines.flow.toCollection
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitSingle
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
@CacheConfig(cacheNames = ["event_likes"])
class EventLikeService(
    private val eventLikeRepository: EventLikeRepository,
    private val securityService: SecurityService
) {
    companion object {
        private val logger = LoggerFactory.getLogger(EventLikeService::class.java)
    }

    suspend fun hasLike(userId: String, eventId: Long): Boolean {
        logger.debug("Request to check if user: $userId has like for event: $eventId")
        return eventLikeRepository.userHasLikedEvent(userId, eventId).awaitFirst()
    }

    suspend fun currentUserHasLike(eventId: Long): Boolean {
        val userId = securityService.extractUserId().awaitFirst()
        return hasLike(userId, eventId)
    }

    @Cacheable(key = "id")
    suspend fun findAll(id: Long): MutableList<EventLike> {
        logger.debug("Request to find all likes for event: $id")
        val likes = mutableListOf<EventLike>()
        eventLikeRepository.findAllByEventId(id).asFlow().toCollection(likes)
        return likes
    }

    suspend fun findAllCurrentUser(): MutableList<EventLike> {
        val userId = securityService.extractUserId().awaitFirst()
        logger.debug("Request to find all likes for user: $userId")
        val likes = mutableListOf<EventLike>()
        eventLikeRepository.findAllByUserId(userId).asFlow().toCollection(likes)
        return likes
    }

    @CachePut(key = "eventLike.eventId")
    suspend fun save(eventLike: EventLike): Int {
        logger.debug("Request to save event like $eventLike")
        eventLikeRepository.save(eventLike).awaitFirst()
        return 1
    }

    suspend fun currentUserSave(eventId: Long): Int {
        val userId = securityService.extractUserId().awaitFirst()
        val userName = securityService.extractUsername().awaitFirst()
        return save(EventLike(eventId, userId, userName))
    }

    suspend fun triggerLike(eventId: Long): Int {
        val userId = securityService.extractUserId().awaitFirst()
        return if (hasLike(userId, eventId)) {
            delete(userId, eventId)
        } else {
            currentUserSave(eventId)
        }
    }

    @CacheEvict(key = "eventId")
    suspend fun delete(userId: String, eventId: Long): Int {
        logger.debug("Request to delete event like for user: $userId and event: $eventId")
        eventLikeRepository.delete(userId, eventId).awaitFirst()
        return -1
    }

    suspend fun currentUserDelete(eventId: Long): Int {
        val userId = securityService.extractUserId().awaitFirst()
        return delete(userId, eventId)
    }

    suspend fun count(id: Long): Long {
        logger.debug("Request to count number of likes for event: $id")
        return eventLikeRepository.count(id).awaitSingle()
    }
}