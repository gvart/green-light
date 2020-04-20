package com.greenlight.eventservice.config

import com.greenlight.common.rsocket.factory.RSocketClientFactory
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


    companion object {
        const val AUTH_SERVICE_CLIENT_BEAN_ID = "authRSocketClient"
    }

    @Bean(AUTH_SERVICE_CLIENT_BEAN_ID)
    fun authRSocketClient(): Mono<RSocketRequester> {
        return RSocketClientFactory().createRSocketClient("auth-service", discoveryClient, rSocketStrategies)
    }
}
