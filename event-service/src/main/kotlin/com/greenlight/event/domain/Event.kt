package com.greenlight.event.domain

import org.springframework.data.annotation.Id
import org.springframework.data.geo.Point
import java.time.LocalDateTime

data class Event(
    @Id
    val id: Long? = null,

    var title: String? = null,
    var description: String,
    var status: EventStatus? = null,
    var geoLocation: Point,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val startsAt: LocalDateTime,
    var updatedAt: LocalDateTime? = null,
    val finishedAt: LocalDateTime? = null,
    var authorId: Long? = null,
    val peopleRequired: Int
)