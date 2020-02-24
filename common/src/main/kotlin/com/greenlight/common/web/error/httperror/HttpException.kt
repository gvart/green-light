package com.greenlight.common.web.error.httperror

/**
 * Base class that is related to HTTP errors
 * @author gvart
 */
open class HttpException(
    private val statusCode: Int,
    private val error: String,
    private var errorMessage: String,
    private val optionalMessages: Map<String, String>? = null
) : Exception(errorMessage) {

    fun getStatusCode() = statusCode
    fun getError() = error
    fun getErrorMessage() = errorMessage
    fun getOptionalMessages() = optionalMessages
}