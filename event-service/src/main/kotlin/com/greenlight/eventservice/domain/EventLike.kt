package com.greenlight.eventservice.domain

import org.springframework.data.relational.core.mapping.Table

@Table
data class EventLike(val eventId: Long, val userId: String, val userName: String)

