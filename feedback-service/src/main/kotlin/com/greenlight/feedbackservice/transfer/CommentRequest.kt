package com.greenlight.feedbackservice.transfer

import javax.validation.constraints.Max
import javax.validation.constraints.NotEmpty

data class CommentRequest(
    @NotEmpty
    @Max(512)
    val content: String,
    val eventId: Long
)