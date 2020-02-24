package com.greenlight.common.web.error.domain

data class ApiError(
    val status: Int,
    val error: String,
    val message: String,
    val optionalMessages: String
)