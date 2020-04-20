package com.greenlight.eventservice.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class EventType(
    @Id
    val id: Int? = null,

    var title: String,
    var description: String
)