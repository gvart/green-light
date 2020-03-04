package com.greenlight.common.service.validation

import javax.validation.ConstraintViolation
import javax.validation.Path


fun <T> Set<ConstraintViolation<T>>.getMessages(): Map<String, String> {
    return mapOf(*this.map {
        Pair<String, String>(
            getPropertyName(
                it.propertyPath
            ), it.message
        )
    }.toTypedArray())
}

private fun getPropertyName(propertyPath: Path): String {
    propertyPath.iterator().next()
    return propertyPath.iterator().next().name
}