package com.greenlight.event.error

class NotFoundException(errorMessage: String) : HttpException(404, errorMessage)
