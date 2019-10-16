package com.greenlight.event.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class Event {

  @Id
  private final Long id;

  private String title;
  private String description;

  private EventStatus status;
  private Point geoLocation;

  private final LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime finishedAt;
  private Long authorId;
}
