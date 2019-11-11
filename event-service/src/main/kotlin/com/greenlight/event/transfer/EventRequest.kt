package com.greenlight.event.transfer

import com.greenlight.event.domain.Event
import com.greenlight.event.domain.Point

import java.time.LocalDateTime
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class EventRequest(
    @NotBlank
    val title: String,
    val description: String,
    @Min(1)
    val statusId: Int,
    @NotNull
    val geoLocation: Point,
    @NotNull
    val startsAt: LocalDateTime,
    @Min(1)
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
