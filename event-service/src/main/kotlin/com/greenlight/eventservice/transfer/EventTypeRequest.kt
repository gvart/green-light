package com.greenlight.eventservice.transfer

import com.greenlight.common.transfer.DomainConverter
import com.greenlight.eventservice.domain.EventType
import javax.validation.constraints.NotBlank

data class EventTypeRequest(
    @get:NotBlank
    val title: String,
    @get:NotBlank
    val description: String
) : DomainConverter<EventType> {
    override fun convert() = EventType(title = title, description = description)
}
