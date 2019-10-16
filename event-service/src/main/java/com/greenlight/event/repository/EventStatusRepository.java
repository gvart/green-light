package com.greenlight.event.repository;

import com.greenlight.event.domain.EventStatus;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface EventStatusRepository extends ReactiveCrudRepository<EventStatus, Integer> {
}
