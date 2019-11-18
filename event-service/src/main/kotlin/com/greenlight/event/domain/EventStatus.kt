package com.greenlight.event.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class EventStatus(
    @Id
    val id: Int,

    val name: String,

    val allowActions: Boolean
)