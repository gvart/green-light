package com.greenlight.event.error

import com.greenlight.event.transfer.EventRequest
import javax.validation.ConstraintViolation

class ValidationException(errorMessage: String, invalidFieldMessages: Set<ConstraintViolation<EventRequest>>) :
    HttpException(400, errorMessage)
