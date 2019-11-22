package com.greenlight.userservice.repository

import com.greenlight.userservice.domain.Event
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface EventRepository : ReactiveMongoRepository<Event, String>