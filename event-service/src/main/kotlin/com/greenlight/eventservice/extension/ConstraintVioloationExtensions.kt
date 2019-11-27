package com.greenlight.eventservice.extension

import javax.validation.ConstraintViolation


fun <T> Set<ConstraintViolation<T>>.getMessages(): List<String> {
    return this.map { it.message }
}