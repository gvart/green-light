package com.greenlight.common.web.error.httperror

class ConflictException(errorMessage: String) : HttpException(409, "Conflict", errorMessage)
