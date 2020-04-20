package com.greenlight.eventservice.transfer.event

import com.greenlight.common.transfer.DomainConverter
import com.greenlight.eventservice.domain.Event
import com.greenlight.eventservice.domain.GeoLocation
import java.time.LocalDateTime
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class EventRequest(
    @get:NotBlank
    val title: String,
    @get:NotBlank
    val content: String,
    @get:NotNull
    val geoLocation: GeoLocation,
    @get:NotNull
    val typeId: Int,
    @get:NotNull
    val startsAt: LocalDateTime,
    @get:Min(1)
    val peopleRequired: Int,
    @get:NotNull
    val items: Set<Int>
) : DomainConverter<Event> {

    override fun convert() = Event(
        title = title,
        content = content,
        geoLocation = geoLocation,
        startsAt = startsAt,
        peopleRequired = peopleRequired,
        type = typeId,
        items = items
    )
}
