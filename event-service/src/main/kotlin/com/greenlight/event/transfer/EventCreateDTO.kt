package com.greenlight.event.transfer

import com.greenlight.event.domain.Event
import org.springframework.data.geo.Point

import java.time.LocalDateTime

data class EventCreateDTO(
    val title: String,
    val description: String,
    val statusId: Int,
    val geoLocation: Point,
    val startsAt: LocalDateTime,
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
