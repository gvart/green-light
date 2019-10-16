package com.greenlight.event.web;

import com.greenlight.event.domain.EventStatus;
import com.greenlight.event.service.EventStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class EventStatusHandler {
  private final EventStatusService service;

  public Mono<ServerResponse> findAll(ServerRequest request) {
    Flux<EventStatus> statuses = service.findAll();
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(statuses, EventStatus.class);
  }
}
