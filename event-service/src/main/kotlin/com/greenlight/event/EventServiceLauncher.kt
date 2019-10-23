package com.greenlight.event

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class EventServiceLauncher

fun main(args: Array<String>) {
    runApplication<EventServiceLauncher>(*args)
}