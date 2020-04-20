package com.greenlight.gateway.fallback

import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping
class FallBackController {

    data class FallBackMessage(val message: String)

    @GetMapping("/fallback")
    suspend fun fallback(): FallBackMessage {
        return Mono.just(FallBackMessage("Service temporary unavailable.")).awaitFirst()
    }
}