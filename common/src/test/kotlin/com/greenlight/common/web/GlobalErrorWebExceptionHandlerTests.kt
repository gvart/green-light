package com.greenlight.common.web

import com.greenlight.common.web.error.GlobalErrorWebExceptionHandler
import com.greenlight.common.web.error.httperror.NotFoundException
import com.greenlight.common.web.error.httperror.ValidationException
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.spy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.ResourceProperties
import org.springframework.boot.autoconfigure.web.ServerProperties
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.http.MediaType
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.reactive.function.server.ServerRequest


@ExtendWith(SpringExtension::class)
open class GlobalErrorWebExceptionHandlerTests {


    private lateinit var globalErrorWebExceptionHandler: GlobalErrorWebExceptionHandler

    @MockBean
    open lateinit var errorAttributes: ErrorAttributes
    @MockBean
    open lateinit var resourceProperties: ResourceProperties
    @MockBean
    open lateinit var serverProperties: ServerProperties
    @Autowired
    open lateinit var applicationContext: ApplicationContext
    @MockBean
    open lateinit var serverCodecConfigurer: ServerCodecConfigurer

    @Mock
    open lateinit var serverRequest: ServerRequest

    @BeforeEach
    open fun setup() {
        globalErrorWebExceptionHandler = spy(
            GlobalErrorWebExceptionHandler(
                errorAttributes,
                resourceProperties,
                serverProperties,
                applicationContext,
                serverCodecConfigurer
            )
        )

    }

    @Test
    open fun `validation exception handled`() {
        val message = "error message"
        `when`(errorAttributes.getError(ArgumentMatchers.any())).thenReturn(ValidationException(message, emptyMap()))
        val result = globalErrorWebExceptionHandler.renderErrorResponse(serverRequest).block()

        Assertions.assertEquals(400, result!!.rawStatusCode())
        Assertions.assertEquals(MediaType.APPLICATION_JSON, result.headers().contentType)
    }

    @Test
    open fun `not found exception handled`() {
        val message = "error message"
        `when`(errorAttributes.getError(ArgumentMatchers.any())).thenReturn(NotFoundException(message))
        val result = globalErrorWebExceptionHandler.renderErrorResponse(serverRequest).block()

        Assertions.assertEquals(404, result!!.rawStatusCode())
        Assertions.assertEquals(MediaType.APPLICATION_JSON, result.headers().contentType)
    }
}