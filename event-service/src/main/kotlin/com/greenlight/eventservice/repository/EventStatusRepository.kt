package com.greenlight.eventservice.repository

import com.greenlight.eventservice.domain.EventStatus
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface EventStatusRepository : R2dbcRepository<EventStatus, Long>