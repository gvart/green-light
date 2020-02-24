package com.greenlight.common.web.handler.service

import kotlinx.coroutines.flow.Flow

/**
 * Base interface for services
 *
 * @param <T> The base Type of repository
 * @param <I> Id Type
 * @author gvart
 */
interface ReadOnlyService<T, I> {
    fun findAll(): Flow<T>

    suspend fun findOne(id: I): T

}
