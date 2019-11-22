package com.greenlight.userservice.transfer

import com.fasterxml.jackson.annotation.JsonFormat
import com.greenlight.userservice.domain.Event
import org.springframework.data.geo.Point

import java.time.LocalDateTime
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class EventRequest(
    @NotBlank
    val title: String,
    val description: String,
    @NotBlank
    val statusId: String,
    @NotNull
    val geoLocation: Point,
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
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
