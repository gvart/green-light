package com.greenlight.eventservice.domain

import com.fasterxml.jackson.annotation.JsonFormat
import javax.persistence.Id
import org.springframework.data.relational.core.mapping.Column
import java.time.LocalDateTime


data class Event(
    @Id
    val id: Long? = null,

    var title: String,

    var description: String,

    @Column("event_status")
    var status: Long? = null,

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