package com.greenlight.event.service

import reactor.core.publisher.Mono

/**
 * Base interface for services
 *
 * @param <T> The base Type of repository
 * @param <D> DTO object for that entity
 * @param <I> Id Type
 * @author gvart
 */
interface ReadWriteService<T, D, I> : ReadOnlyService<T, I> {
    fun save(entity: Mono<D>): Mono<T>

    fun update(id: I, entity: Mono<D>): Mono<T>

    fun delete(id: I): Mono<Void>
}
