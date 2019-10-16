package com.greenlight.event.repository;

import com.greenlight.event.domain.Event;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EventRepository extends ReactiveCrudRepository<Event, Long> {}
