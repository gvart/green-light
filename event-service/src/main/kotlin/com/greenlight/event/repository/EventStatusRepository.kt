package com.greenlight.event.repository

import com.greenlight.event.domain.EventStatus
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface EventStatusRepository : ReactiveCrudRepository<EventStatus, Int>