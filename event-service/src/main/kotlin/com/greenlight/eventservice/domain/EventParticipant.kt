package com.greenlight.eventservice.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class EventParticipant(
    @Id
    val id: Long? = null,
    var eventId: Long,
    var userId: String?,
    var userName: String? = null
)