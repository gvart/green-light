package com.greenlight.event.service;

import reactor.core.publisher.Mono;

/**
 * Base interface for services
 *
 * @param <T> The base Type of repository
 * @param <D> DTO object for that entity
 * @param <I> Id Type
 * @author gvart
 */
public interface ReadWriteService<T, D, I> extends ReadOnlyService<T, I> {
  Mono<T> createOne(Mono<D> dto);

  Mono<T> updateOne(I id, D entity);

  Mono<Void> deleteOne(I id);
}
