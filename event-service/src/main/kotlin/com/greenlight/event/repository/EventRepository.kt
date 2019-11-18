package com.greenlight.event.repository

import com.greenlight.event.domain.Event
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface EventRepository : ReactiveMongoRepository<Event, Long>