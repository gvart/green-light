package com.greenlight.eventservice.repository

import com.greenlight.eventservice.domain.UserProfile
import org.springframework.data.r2dbc.repository.R2dbcRepository

interface UserProfileRepository : R2dbcRepository<UserProfile, Long>