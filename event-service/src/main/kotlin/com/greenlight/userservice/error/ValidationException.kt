package com.greenlight.userservice.error

class ValidationException(errorMessage: String, invalidFieldMessages: List<String>) :
    HttpException(400, errorMessage, invalidFieldMessages)
