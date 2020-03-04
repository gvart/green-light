package com.greenlight.common.web.error

import com.greenlight.common.web.error.httperror.HttpException
import com.greenlight.common.web.error.httperror.NotFoundException
import com.greenlight.common.web.error.httperror.ValidationException
import io.rsocket.exceptions.ApplicationErrorException
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
import org.springframework.web.reactive.function.server.RouterFunction
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

    override fun getRoutingFunction(errorAttributes: ErrorAttributes): RouterFunction<ServerResponse> =
        route(all(), HandlerFunction { renderErrorResponse(it) })

    public override fun renderErrorResponse(request: ServerRequest): Mono<ServerResponse> {
        val error = getError(request)
        val errorAttributes = getErrorAttributes(request, false)
        if (error is ApplicationErrorException) {
            errorAttributes["status"] = 400
            errorAttributes["error"] = "Invalid data passed"
            errorAttributes["message"] = error.message
        } else if (error is HttpException) {
            errorAttributes["status"] = error.getStatusCode()
            errorAttributes["error"] = error.getError()
            errorAttributes["message"] = error.getErrorMessage()

            if (error.getOptionalMessages() != null) {
                errorAttributes["optionalMessages"] = error.getOptionalMessages()
            }
        }

        return ServerResponse.status(errorAttributes["status"] as Int).contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(errorAttributes))
    }
}