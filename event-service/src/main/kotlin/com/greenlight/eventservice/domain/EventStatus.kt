package com.greenlight.eventservice.domain

import javax.persistence.Id

data class EventStatus(
    @Id
    val id: Long?,

    val name: String,

    val allowActions: Boolean
)