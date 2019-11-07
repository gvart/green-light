package com.greenlight.event.validation

import com.greenlight.event.error.ValidationException
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import javax.validation.Validator

@Component
class ValidationUtil(
    private val validator: Validator
) {


    fun <T> validate(entity: T): Mono<T> {
        val validationResult = validator.validate(entity)
        if (validationResult.isNotEmpty()) {
            return Mono.error {
                ValidationException("Invalid fields", validationResult.map { it.message })
            }
        }
        return Mono.just(entity)
    }
}