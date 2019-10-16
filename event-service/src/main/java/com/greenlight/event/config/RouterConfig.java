package com.greenlight.event.config;

import com.greenlight.event.web.EventStatusHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
@RequiredArgsConstructor
public class RouterConfig {

  private final EventStatusHandler eventStatusHandler;

  @Bean
  public RouterFunction<ServerResponse> router() {
    return RouterFunctions.route()
        .path("/api/v1", builder -> builder.add(eventStatusRoutes()))
        .build();
  }

  private RouterFunction<ServerResponse> eventStatusRoutes() {
    return RouterFunctions.route()
        .path(
            "/event-status",
            mainBuilder ->
                mainBuilder.nest(
                    accept(MediaType.APPLICATION_JSON),
                    builder -> builder.GET("", eventStatusHandler::findAll)))
        .build();
  }
}
