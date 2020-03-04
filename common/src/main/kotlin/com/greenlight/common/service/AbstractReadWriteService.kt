package com.greenlight.common.service

import com.greenlight.common.service.validation.getMessages
import com.greenlight.common.web.error.httperror.ValidationException
import javax.validation.Validator

/**
 * Base interface for services
 *
 * @param <T> The base Type of repository
 * @param <D> DTO object for that entity
 * @param <I> Id Type
 * @author gvart
 */
abstract class AbstractReadWriteService<T, D, I>(private val validator: Validator) :
    AbstractReadOnlyService<T, I>() {

    protected abstract fun saveEntity(request: D): T

    protected abstract fun updateEntity(id: I, request: D): T

    protected abstract fun deleteEntity(id: I)

    fun save(request: D): T {
        getLogger().debug("Request to save entity: $request")
        validateRequest(request)
        return saveEntity(request)
    }

    fun update(id: I, request: D): T {
        getLogger().debug("Request to update entity: $request, and id: $id")
        validateRequest(request)
        return updateEntity(id, request)
    }

    fun delete(id: I) {
        getLogger().debug("Request to delete entity by id: $id")
        deleteEntity(id)
    }


    private fun validateRequest(request: D) {
        val validationResult = validator.validate(request)
        if (validationResult.isNotEmpty()) {
            getLogger().error("Validation error {}", validationResult)
            throw ValidationException("Invalid object", validationResult.getMessages())
        }
    }
}
