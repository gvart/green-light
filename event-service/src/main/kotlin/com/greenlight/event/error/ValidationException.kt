package com.greenlight.event.error

class ValidationException(errorMessage: String, invalidFieldMessages: List<String>) : HttpException(400, errorMessage)
