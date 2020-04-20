package com.greenlight.eventservice.transfer.event

import com.greenlight.common.transfer.DomainConverter
import com.greenlight.eventservice.domain.EventParticipant
import javax.validation.constraints.NotNull

data class EventParticipantRequest(
    @NotNull
    val eventId: Long
) : DomainConverter<EventParticipant> {
    override fun convert(): EventParticipant {
        return EventParticipant(null, eventId, null)
    }
}