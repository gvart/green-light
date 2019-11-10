package com.greenlight.event.web

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

interface CRUDHandler {
    suspend fun save(request: ServerRequest): ServerResponse
    suspend fun update(request: ServerRequest): ServerResponse
    suspend fun findAll(request: ServerRequest): ServerResponse
    suspend fun findOne(request: ServerRequest): ServerResponse
    suspend fun delete(request: ServerRequest): ServerResponse
}