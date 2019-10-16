package com.greenlight.event.service;

import com.greenlight.event.domain.EventStatus;
import com.greenlight.event.repository.EventStatusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventStatusService implements ReadOnlyService<EventStatus, Integer> {
  private final EventStatusRepository eventStatusRepository;

  @Override
  public Flux<EventStatus> findAll() {
    return eventStatusRepository.findAll();
  }

  @Override
  public Mono<EventStatus> findOne(Integer id) {
    return eventStatusRepository.findById(id);
  }
}
