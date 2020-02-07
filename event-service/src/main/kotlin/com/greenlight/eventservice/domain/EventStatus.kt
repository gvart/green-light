package com.greenlight.eventservice.domain

import org.springframework.data.annotation.Id

data class EventStatus(
    @Id
    val id: Long?,

    val name: String,

    val allowActions: Boolean
)