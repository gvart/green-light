package com.greenlight.eventservice.transfer.event

import com.greenlight.eventservice.domain.Event
import com.greenlight.eventservice.domain.EventStatus
import com.greenlight.eventservice.domain.EventType
import com.greenlight.eventservice.domain.GeoLocation
import java.time.LocalDateTime

data class EventShortDetailsResponse(
    val id: Long,
    val title: String,
    val status: EventStatus,
    val type: EventType,
    val geoLocation: GeoLocation,
    val createdAt: LocalDateTime,
    val userId: String,
    val userName: String,
    var peopleRequired: Int,
    var likes: Int,
    var isLiked: Boolean
) {
    constructor(event: Event, status: EventStatus, type: EventType, isLiked: Boolean) : this(
        id = event.id!!,
        title = event.title,
        geoLocation = event.geoLocation,
        createdAt = event.createdAt,
        userId = event.userId!!,
        userName = event.userName!!,
        peopleRequired = event.peopleRequired,
        likes = event.likes,
        status = status,
        type = type,
        isLiked = isLiked
    )
}