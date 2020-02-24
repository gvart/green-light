package com.greenlight.common.web.handler.service

/**
 * Base interface for services
 *
 * @param <T> The base Type of repository
 * @param <D> DTO object for that entity
 * @param <I> Id Type
 * @author gvart
 */
interface ReadWriteService<T, D, I> : ReadOnlyService<T, I> {
    suspend fun save(request: D): T

    suspend fun update(id: I, request: D): T

    suspend fun delete(id: I): Void
}
