package com.greenlight.eventservice.transfer.event

import com.greenlight.eventservice.domain.Event
import com.greenlight.eventservice.domain.EventStatus
import com.greenlight.eventservice.domain.EventType
import com.greenlight.eventservice.domain.GeoLocation
import java.time.LocalDateTime

data class EventDetailsResponse(
    val id: Long,
    val title: String,
    val content: String,
    val status: EventStatus,
    val type: EventType,
    val geoLocation: GeoLocation,
    val createdAt: LocalDateTime,
    val startsAt: LocalDateTime,
    val updatedAt: LocalDateTime?,
    val finishedAt: LocalDateTime?,
    val userId: String,
    val userName: String,
    val peopleRequired: Int,
    val peopleJoined: Int,
    val likes: Long,
    val isLiked: Boolean,
    val items: Set<Int>
) {
    constructor(
        event: Event,
        status: EventStatus,
        type: EventType,
        peopleJoined: Int,
        likes: Long,
        isLiked: Boolean
    ) : this(
        id = event.id!!,
        title = event.title,
        content = event.content,
        status = status,
        type = type,
        geoLocation = event.geoLocation,
        createdAt = event.createdAt,
        updatedAt = event.updatedAt,
        startsAt = event.startsAt,
        finishedAt = event.finishedAt,
        userId = event.userId!!,
        userName = event.userName!!,
        peopleRequired = event.peopleRequired,
        peopleJoined = peopleJoined,
        likes = likes,
        isLiked = isLiked,
        items = event.items
    )
}