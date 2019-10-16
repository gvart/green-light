package com.greenlight.event.transfer;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.geo.Point;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class EventDTO {
  private String title;
  private String description;

  private int statusId;
  private Point geoLocation;

  private LocalDateTime date;
}
