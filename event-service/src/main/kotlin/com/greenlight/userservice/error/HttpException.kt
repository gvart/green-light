package com.greenlight.userservice.error

/**
 * Base class that is related to HTTP errors
 * @author gvart
 */
open class HttpException(
    private val statusCode: Int,
    private val errorMessage: String,
    private val optionalMessages: List<String> = emptyList()
) : Exception(errorMessage) {
    fun getStatusCode() = statusCode
}