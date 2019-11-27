package com.greenlight.eventservice.domain

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class Event(
    @Id
    val id: String? = null,

    var title: String,

    var description: String,

    @DBRef
    var status: EventStatus? = null,

    var geoLocation: Point,

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    val startsAt: LocalDateTime,

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    var updatedAt: LocalDateTime? = null,

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    val finishedAt: LocalDateTime? = null,

    var authorId: Long? = null,

    var peopleRequired: Int
)