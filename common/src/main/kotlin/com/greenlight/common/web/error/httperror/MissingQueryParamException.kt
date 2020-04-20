package com.greenlight.common.web.error.httperror

class MissingQueryParamException(paramName: String) :
    HttpException(400, "Bad Request", "Param $paramName is missing.")
