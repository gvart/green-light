package com.greenlight.userservice.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class EventStatus(
    @Id
    val id: String?,

    val name: String,

    val allowActions: Boolean
)