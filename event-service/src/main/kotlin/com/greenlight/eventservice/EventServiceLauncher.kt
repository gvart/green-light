package com.greenlight.eventservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.ComponentScan

@EnableCaching
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan("com.greenlight.*")
class EventServiceLauncher

fun main(args: Array<String>) {
    runApplication<EventServiceLauncher>(*args)
}