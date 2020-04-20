package com.greenlight.eventservice.service

import com.greenlight.common.service.AbstractCoroutineReadOnlyService
import com.greenlight.common.web.error.httperror.NotFoundException
import com.greenlight.eventservice.domain.EventItem
import com.greenlight.eventservice.repository.EventItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrElse
import kotlinx.coroutines.reactive.awaitSingle
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class EventItemService(
    private val repository: EventItemRepository
) : AbstractCoroutineReadOnlyService<EventItem, Int>() {

    companion object {
        private val logger = LoggerFactory.getLogger(EventItemService::class.java)
    }

    override fun findAllEntities(): Flow<EventItem> {
        return repository.findAll().asFlow()
    }

    override suspend fun findOneEntity(id: Int): EventItem {
        return repository.findById(id)
            .awaitFirstOrElse { throw NotFoundException("Event Item $id not found") }
    }

    override fun getLogger(): Logger {
        return logger
    }

}