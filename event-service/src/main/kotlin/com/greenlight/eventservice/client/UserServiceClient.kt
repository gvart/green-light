package com.greenlight.eventservice.client


interface UserServiceClient {
    suspend fun existsById(id: String): Boolean
}