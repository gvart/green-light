package com.greenlight.eventservice.repository

import com.greenlight.eventservice.domain.EventType
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface EventTypeRepository : R2dbcRepository<EventType, Int>


enum class DefaultEventType(val id: Long) {
    CREATED(1),
    IN_PROGRESS(2),
    FINISHED(3),
    CREATION_IN_PROGRESS(4),
    CANCELED(5);
}