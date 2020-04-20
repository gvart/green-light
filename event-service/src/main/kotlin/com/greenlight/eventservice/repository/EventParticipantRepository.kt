package com.greenlight.eventservice.repository

import com.greenlight.eventservice.domain.EventParticipant
import kotlinx.coroutines.flow.Flow
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface EventParticipantRepository : R2dbcRepository<EventParticipant, Long> {
    @Query("SELECT * FROM event_participant WHERE event_id = :id")
    fun findAllByEventId(id: Long): Flow<EventParticipant>
}