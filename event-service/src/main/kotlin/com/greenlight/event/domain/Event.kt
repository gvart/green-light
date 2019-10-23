package com.greenlight.event.domain

import org.springframework.data.annotation.Id
import org.springframework.data.geo.Point
import java.time.LocalDateTime
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class Event(
    @Id
    val id: Long? = null,

    @NotBlank
    var title: String? = null,

    var description: String,

    @NotNull
    var status: EventStatus? = null,

    @NotNull
    var geoLocation: Point,

    @NotNull
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @NotNull
    val startsAt: LocalDateTime,

    var updatedAt: LocalDateTime? = null,

    val finishedAt: LocalDateTime? = null,

    @NotNull
    var authorId: Long? = null,

    @Min(1)
    val peopleRequired: Int
)