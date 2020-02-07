package com.greenlight.eventservice.transfer

import com.fasterxml.jackson.annotation.JsonFormat
import com.greenlight.eventservice.domain.Event
import com.greenlight.eventservice.domain.Point

import java.time.LocalDateTime
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class EventRequest(
    @get:NotBlank
    val title: String,
    val description: String,
    @get:NotNull
    val statusId: Long,
    @get:NotNull
    val geoLocation: Point,
    @get:NotNull
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    val startsAt: LocalDateTime,
    @get:Min(1)
    val peopleRequired: Int
) : DomainConverter<Event> {

    override fun convert() = Event(
        title = title,
        description = description,
        geoLocation = geoLocation,
        startsAt = startsAt,
        peopleRequired = peopleRequired
    )
}
