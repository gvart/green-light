package com.greenlight.common.web.error.httperror

open class ValidationException(errorMessage: String, invalidFieldMessages: Map<String, String>) :
    HttpException(400, "Bad Request", errorMessage, invalidFieldMessages)
