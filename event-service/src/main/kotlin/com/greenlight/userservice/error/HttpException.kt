package com.greenlight.userservice.error

/**
 * Base class that is related to HTTP errors
 * @author gvart
 */
open class HttpException(private val statusCode: Int, errorMessage: String) : Exception(errorMessage) {
    fun getStatusCode() = statusCode
}