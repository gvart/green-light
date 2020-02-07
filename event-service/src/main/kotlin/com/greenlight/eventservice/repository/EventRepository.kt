package com.greenlight.eventservice.repository

import com.greenlight.eventservice.domain.Event
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface EventRepository : R2dbcRepository<Event, Long>