package com.greenlight.event.config

import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig {

    @LoadBalanced
    fun loadBalancedWebClient() = WebClient.builder()
}