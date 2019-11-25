package com.greenlight.userservice.config

import com.greenlight.userservice.error.HttpException
import com.greenlight.userservice.error.NotFoundException
import com.greenlight.userservice.error.ValidationException
import org.springframework.boot.autoconfigure.web.ResourceProperties
import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.core.annotation.Order
import org.springframework.http.MediaType
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.RequestPredicates.all
import org.springframework.web.reactive.function.server.RouterFunctions.route
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Order(-2)
@Component
class GlobalErrorWebExceptionHandler(
    errorAttributes: ErrorAttributes,
    resourceProperties: ResourceProperties,
    serverProperties: ServerProperties,
    applicationContext: ApplicationContext,
    serverCodecConfigurer: ServerCodecConfigurer
) : DefaultErrorWebExceptionHandler(errorAttributes, resourceProperties, serverProperties.error, applicationContext) {

    init {
        super.setMessageWriters(serverCodecConfigurer.writers)
    }

    override fun getRoutingFunction(errorAttributes: ErrorAttributes) =
        route(all(), HandlerFunction { renderErrorResponse(it) })

    override fun renderErrorResponse(request: ServerRequest): Mono<ServerResponse> {
        val error = getError(request)
        val errorAttributes = getErrorAttributes(request, false)
        val status = when (error) {
            is ValidationException -> 400
            is NotFoundException -> 404
            else -> getHttpStatus(errorAttributes)
        }
        if (error is HttpException) {
            errorAttributes["message"] = error.message
        }

        return ServerResponse.status(status).contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(errorAttributes))
    }
}