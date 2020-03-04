package com.greenlight.common.service

import kotlinx.coroutines.flow.Flow
import org.slf4j.Logger

/**
 * Base interface for services
 *
 * @param <T> The base Type of repository
 * @param <I> Id Type
 * @author gvart
 */
abstract class AbstractCoroutineReadOnlyService<T, I> {

    fun findAll(): Flow<T> {
        getLogger().debug("Request to find all entities.")
        return findAllEntities()
    }

    protected abstract fun findAllEntities(): Flow<T>

    suspend fun findOne(id: I): T {
        getLogger().debug("Request to find one entity by id: $id")
        return findOneEntity(id)
    }

    protected abstract suspend fun findOneEntity(id: I): T

    abstract fun getLogger(): Logger
}
