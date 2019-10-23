package com.greenlight.event.domain

import org.springframework.data.annotation.Id

data class EventStatus(
        @Id val id: Int,
        val name: String,
        val allowActions: Boolean
)