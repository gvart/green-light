package com.greenlight.event

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@EnableDiscoveryClient
@SpringBootApplication
class EventServiceLauncher

fun main(args: Array<String>) {
    runApplication<EventServiceLauncher>(*args)
}