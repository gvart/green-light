package com.greenlight.eventservice.repository

import com.greenlight.eventservice.domain.Event
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface EventRepository : ReactiveMongoRepository<Event, String>