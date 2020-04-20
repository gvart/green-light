package com.greenlight.eventservice.repository

import com.greenlight.eventservice.domain.EventItem
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface EventItemRepository : R2dbcRepository<EventItem, Int>