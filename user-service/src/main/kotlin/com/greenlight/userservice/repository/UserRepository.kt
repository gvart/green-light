package com.greenlight.userservice.repository

import com.greenlight.userservice.domain.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface UserRepository : ReactiveMongoRepository<User, String>