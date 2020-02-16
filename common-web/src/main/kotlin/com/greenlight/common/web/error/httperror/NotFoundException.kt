package com.greenlight.common.web.error.httperror

class NotFoundException(errorMessage: String) : HttpException(404, "Not Found", errorMessage)
