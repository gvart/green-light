package com.greenlight.event.domain

import org.springframework.data.annotation.Id
import java.time.LocalDateTime

data class Event(
    @Id
    val id: Long? = null,

    var title: String,
    var description: String,
    var status: EventStatus? = null,
    var geoLocation: Point,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val startsAt: LocalDateTime,
    var updatedAt: LocalDateTime? = null,
    val finishedAt: LocalDateTime? = null,
    var authorId: Long? = null,
    var peopleRequired: Int
)