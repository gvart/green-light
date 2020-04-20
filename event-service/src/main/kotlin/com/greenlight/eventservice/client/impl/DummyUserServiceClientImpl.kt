package com.greenlight.eventservice.client.impl

import com.greenlight.eventservice.client.UserServiceClient
import org.springframework.stereotype.Service

@Service
class DummyUserServiceClientImpl : UserServiceClient {
    override suspend fun existsById(id: String): Boolean {
        return true
    }
}