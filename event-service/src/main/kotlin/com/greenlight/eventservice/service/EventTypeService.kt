package com.greenlight.eventservice.service

import com.greenlight.common.service.AbstractCoroutineReadWriteService
import com.greenlight.common.web.error.httperror.NotFoundException
import com.greenlight.eventservice.domain.EventType
import com.greenlight.eventservice.repository.EventTypeRepository
import com.greenlight.eventservice.transfer.EventTypeRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrElse
import kotlinx.coroutines.reactive.awaitSingle
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.validation.Validator

@Service
class EventTypeService(
    private val eventTypeRepository: EventTypeRepository,
    validator: Validator
) : AbstractCoroutineReadWriteService<EventType, EventTypeRequest, Int>(validator) {

    companion object {
        private val logger = LoggerFactory.getLogger(EventTypeService::class.java)
    }

    override fun findAllEntities(): Flow<EventType> {
        return eventTypeRepository.findAll().asFlow()
    }

    override suspend fun findOneEntity(id: Int): EventType = eventTypeRepository.findById(id)
        .awaitFirstOrElse { throw NotFoundException("EventType $id not found") }


    override suspend fun saveEntity(request: EventTypeRequest): EventType {
        val eventType = request.convert()
        return eventTypeRepository.save(eventType).awaitSingle()
    }


    override suspend fun updateEntity(id: Int, request: EventTypeRequest): EventType {
        val eventType = findOne(id)

        eventType.apply {
            title = request.title
            description = request.description
        }

        return eventTypeRepository.save(eventType).awaitSingle()
    }

    override suspend fun deleteEntity(id: Int) {
        eventTypeRepository.deleteById(id).awaitSingle()
    }

    override fun getLogger(): Logger {
        return logger
    }

}
