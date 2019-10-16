package com.greenlight.event.service;

import com.greenlight.event.domain.Event;
import com.greenlight.event.repository.EventRepository;
import com.greenlight.event.repository.EventStatusRepository;
import com.greenlight.event.transfer.EventDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventService implements ReadWriteService<Event, EventDTO, Long> {
  private final EventRepository eventRepository;
  private final EventStatusRepository eventStatusRepository;

  @Override
  public Flux<Event> findAll() {
    return eventRepository.findAll();
  }

  @Override
  public Mono<Event> findOne(Long id) {
    return eventRepository.findById(id);
  }

  @Override
  public Mono<Event> createOne(Mono<EventDTO> body) {
    // todo finish & refactor
    return body.flatMap(
        dto ->
            eventStatusRepository
                .findById(dto.getStatusId())
                .flatMap(
                    status -> {
                      var event = new Event(null, LocalDateTime.now());
                      event.setAuthorId(2L);
                      event.setTitle(dto.getTitle());
                      event.setDescription(dto.getDescription());
                      event.setStatus(status);
                      event.setGeoLocation(dto.getGeoLocation());
                      return eventRepository.save(event);
                    }));
  }

  @Override
  public Mono<Event> updateOne(Long id, EventDTO entity) {
    return null;
  }

  @Override
  public Mono<Void> deleteOne(Long id) {
    return null;
  }
}
