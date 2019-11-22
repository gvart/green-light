package com.greenlight.userservice.error

import com.greenlight.userservice.transfer.EventRequest
import javax.validation.ConstraintViolation

class ValidationException(errorMessage: String, invalidFieldMessages: Set<ConstraintViolation<EventRequest>>) :
    HttpException(400, errorMessage)
