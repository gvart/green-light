package com.greenlight.eventservice.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class EventStatus(
    @Id
    val id: Long?,

    val name: String,

    val allowActions: Boolean
)