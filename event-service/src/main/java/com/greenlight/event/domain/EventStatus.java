package com.greenlight.event.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@RequiredArgsConstructor
public class EventStatus {
    @Id
    private final int id;
    private final String name;
    private final boolean allowActions;
}
