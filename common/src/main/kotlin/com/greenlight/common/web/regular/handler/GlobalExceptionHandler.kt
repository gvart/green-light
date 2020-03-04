//package com.greenlight.common.web.regular.handler
//
//import com.greenlight.common.web.error.domain.ApiError
//import com.greenlight.common.web.error.httperror.ConflictException
//import com.greenlight.common.web.error.httperror.HttpException
//import com.greenlight.common.web.error.httperror.NotFoundException
//import com.greenlight.common.web.error.httperror.ValidationException
//import org.springframework.http.HttpStatus
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.ExceptionHandler
//import org.springframework.web.context.request.WebRequest
//import org.springframework.web.util.WebUtils
//
//class GlobalExceptionHandler {
//    @ExceptionHandler(NotFoundException::class, ConflictException::class, ValidationException::class)
//    fun handleException(ex: Exception, request: WebRequest): ResponseEntity<ApiError> {
//        return when (ex) {
//            is NotFoundException -> {
//                handleDefinedErrors(ex, HttpStatus.NOT_FOUND, request)
//            }
//            is ConflictException -> {
//                handleDefinedErrors(ex, HttpStatus.CONFLICT, request)
//            }
//            else -> {
//                handleExceptionInternal(ex, null, HttpStatus.INTERNAL_SERVER_ERROR, request)
//            }
//        }
//    }
//
//    private fun handleDefinedErrors(
//        ex: HttpException,
//        request: WebRequest
//    ): ResponseEntity<ApiError> {
//        return handleExceptionInternal(ex, , HttpStatus.valueOf(ex.getStatusCode()), request)
//    }
//
//    private fun handleExceptionInternal(
//        ex: Exception,
//        body: ApiError,
//        status: HttpStatus,
//        request: WebRequest
//    ): ResponseEntity<ApiError> {
//
//        if (HttpStatus.INTERNAL_SERVER_ERROR == status) {
//            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST)
//        }
//        return ResponseEntity(body, status)
//    }
//}