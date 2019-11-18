package com.greenlight.event.repository

import com.greenlight.event.domain.EventStatus
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface EventStatusRepository : ReactiveMongoRepository<EventStatus, Int>