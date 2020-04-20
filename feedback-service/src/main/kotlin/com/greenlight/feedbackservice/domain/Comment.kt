package com.greenlight.feedbackservice.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table
data class Comment(
    @Id
    val id: Long? = null,
    val eventId: Long,
    val userId: String,
    val userName: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime? = null,
    var content: String
)