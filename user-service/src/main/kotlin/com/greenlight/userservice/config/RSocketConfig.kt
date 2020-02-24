package com.greenlight.userservice.config

import com.greenlight.common.rsocket.factory.RSocketClientFactory
import io.rsocket.client.LoadBalancedRSocketMono
import org.springframework.cloud.client.discovery.DiscoveryClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.messaging.rsocket.RSocketStrategies
import reactor.core.publisher.Mono

@Configuration
class RSocketConfig(
    private val discoveryClient: DiscoveryClient,
    private val rSocketStrategies: RSocketStrategies
) {

    @Bean("eventRSocketService")
    fun eventRSocketService(): Mono<RSocketRequester> {
        return RSocketClientFactory().createRSocketClient("event-service", discoveryClient, rSocketStrategies)
    }
}
