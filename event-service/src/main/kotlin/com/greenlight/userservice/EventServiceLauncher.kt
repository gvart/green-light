package com.greenlight.userservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication
class EventServiceLauncher

fun main(args: Array<String>) {
    runApplication<EventServiceLauncher>(*args)
}