package com.greenlight.common.web.error.httperror

class ValidationException(errorMessage: String, invalidFieldMessages: List<String>) :
    HttpException(400, errorMessage, invalidFieldMessages)
