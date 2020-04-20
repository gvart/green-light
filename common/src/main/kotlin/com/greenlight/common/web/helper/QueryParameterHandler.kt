package com.greenlight.common.web.helper

import com.greenlight.common.web.error.httperror.MissingQueryParamException
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

open class QueryParameterHandler {
    companion object {
        suspend fun handle(
            parameterName: String,
            request: ServerRequest,
            vararg handlerQueryMappedValues: QueryMappedValue
        ): ServerResponse {
            val queryParam = request.queryParam(parameterName)
            if (queryParam.isPresent) {
                val queryParameterValue = queryParam.get()
                for (function in handlerQueryMappedValues) {
                    if (function.parameterValue == queryParameterValue) {
                        return function.serverResponse.invoke()
                    }
                }
            }

            throw MissingQueryParamException(parameterName)
        }
    }

    data class QueryMappedValue(val parameterValue: String, val serverResponse: suspend () -> ServerResponse)
}