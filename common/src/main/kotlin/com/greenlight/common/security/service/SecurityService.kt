package com.greenlight.common.security.service

import reactor.core.publisher.Mono

interface SecurityService {
    fun extractUserId(): Mono<Long>
}