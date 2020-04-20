package com.greenlight.eventservice.client.impl

import com.greenlight.eventservice.client.UserServiceClient

class UserServiceClientImpl : UserServiceClient {
    suspend override fun existsById(id: String): Boolean {
        TODO("Not yet implemented")
    }
}