package com.greenlight.feedbackservice.repository

import com.greenlight.feedbackservice.domain.Comment
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Flux

interface CommentRepository : R2dbcRepository<Comment, Long> {

    @Query("SELECT * FROM comment WHERE event_id = :eventId")
    fun findAllByEventId(eventId: Long): Flux<Comment>
}