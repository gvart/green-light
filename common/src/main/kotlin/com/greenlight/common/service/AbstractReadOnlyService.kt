package com.greenlight.common.service

import org.slf4j.Logger

/**
 * Base interface for services
 *
 * @param <T> The base Type of repository
 * @param <I> Id Type
 * @author gvart
 */
abstract class AbstractReadOnlyService<T, I> {

    fun findAll(): Collection<T> {
        getLogger().debug("Request to find all entities.")
        return findAllEntities()
    }

    protected abstract fun findAllEntities(): Collection<T>

    fun findOne(id: I): T {
        getLogger().debug("Request to find one entity by id: $id")
        return findOneEntity(id)
    }

    protected abstract fun findOneEntity(id: I): T

    abstract fun getLogger(): Logger
}
