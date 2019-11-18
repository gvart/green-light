package com.greenlight.event.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class Event(
    @Id
    val id: Long? = null,

    var title: String,

    var description: String,

    @DBRef
    var status: EventStatus? = null,

    var geoLocation: Point,

    val createdAt: LocalDateTime = LocalDateTime.now(),

    val startsAt: LocalDateTime,

    var updatedAt: LocalDateTime? = null,

    val finishedAt: LocalDateTime? = null,

    var authorId: Long? = null,

    var peopleRequired: Int
)