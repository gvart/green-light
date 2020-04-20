package com.greenlight.eventservice.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table
data class Event(
    @Id
    val id: Long? = null,

    var title: String,

    var content: String,

    @Column("event_status")
    var status: Long? = null,

    @Column("event_type")
    var type: Int? = null,

    var geoLocation: GeoLocation,

    val createdAt: LocalDateTime = LocalDateTime.now(),

    val startsAt: LocalDateTime,

    var updatedAt: LocalDateTime? = null,

    val finishedAt: LocalDateTime? = null,

    var userId: String? = null,

    var userName: String? = null,

    var peopleRequired: Int,

    var likes: Int = 0,

    var items: Set<Int>
)