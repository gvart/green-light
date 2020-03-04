package com.greenlight.common.web.handler

import com.greenlight.common.service.AbstractCoroutineReadWriteService
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactor.asFlux
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait


abstract class CRUDHandler<T : Any, D, I>(private val readWriteService: AbstractCoroutineReadWriteService<T, D, I>) {

    abstract suspend fun extractBody(request: ServerRequest): D
    abstract suspend fun extractId(request: ServerRequest): I

    open suspend fun save(request: ServerRequest): ServerResponse {
        val body = extractBody(request)
        val savedEntity = readWriteService.save(body)
        return ServerResponse.status(HttpStatus.CREATED)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(savedEntity)
    }

    open suspend fun update(request: ServerRequest): ServerResponse {
        val id = extractId(request)
        val body = extractBody(request)
        val savedEntity = readWriteService.update(id, body)
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(savedEntity)
    }

    suspend fun findAll(request: ServerRequest): ServerResponse {
        val events = readWriteService.findAll()
        val bodyFromPublisher = BodyInserters.fromPublisher(events.asFlux(),
            object : ParameterizedTypeReference<T>() {})
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON).body(bodyFromPublisher).awaitFirst()
    }

    suspend fun findOne(request: ServerRequest): ServerResponse {
        val id = extractId(request)
        val foundEntity = readWriteService.findOne(id)
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(foundEntity)
    }

    suspend fun delete(request: ServerRequest): ServerResponse {
        val id = extractId(request)
        readWriteService.delete(id)
        return ServerResponse.status(HttpStatus.NO_CONTENT).buildAndAwait()
    }
}