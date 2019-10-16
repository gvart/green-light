package com.greenlight.event.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Base interface for services
 *
 * @param <T> The base Type of repository
 * @param <I> Id Type
 * @author gvart
 */
public interface ReadOnlyService<T,I> {
  Flux<T> findAll();

  Mono<T> findOne(I id);

}
