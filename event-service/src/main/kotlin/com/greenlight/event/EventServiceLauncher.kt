package com.greenlight.event

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient


@EnableEurekaClient
@SpringBootApplication
class EventServiceLauncher

fun main(args: Array<String>) {
    runApplication<EventServiceLauncher>(*args)
}