package com.greenlight.userservice.error

class NotFoundException(errorMessage: String) : HttpException(404, errorMessage)
