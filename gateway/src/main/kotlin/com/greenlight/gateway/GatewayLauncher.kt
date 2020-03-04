package com.greenlight.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GatewayLauncher

fun main(args: Array<String>) {
    runApplication<GatewayLauncher>(*args)
}