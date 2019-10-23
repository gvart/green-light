package com.greenlight.event.repository

import com.greenlight.event.domain.Event
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface EventRepository : ReactiveCrudRepository<Event, Long>