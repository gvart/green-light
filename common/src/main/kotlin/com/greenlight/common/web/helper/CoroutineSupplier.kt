package com.greenlight.common.web.helper

interface CoroutineSupplier<T> {
    suspend fun get(): T
}