package com.greenlight.userservice.repository

import com.greenlight.userservice.domain.EventStatus
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface EventStatusRepository : ReactiveMongoRepository<EventStatus, String>