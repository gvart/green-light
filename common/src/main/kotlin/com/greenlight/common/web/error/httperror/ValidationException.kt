package com.greenlight.common.web.error.httperror

class ValidationException(errorMessage: String, invalidFieldMessages: Map<String, String>) :
    HttpException(400, "Bad Request", errorMessage, invalidFieldMessages)
