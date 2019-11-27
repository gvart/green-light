package com.greenlight.eventservice.repository

import com.greenlight.eventservice.domain.EventStatus
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface EventStatusRepository : ReactiveMongoRepository<EventStatus, String>