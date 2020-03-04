package com.greenlight.common.web.error.httperror

class DuplicateEntityException(fieldName: String, value: String) :
    ValidationException(
        "Entity already exists",
        mapOf(Pair(fieldName, "${fieldName.capitalize()} '$value' already exists"))
    )
