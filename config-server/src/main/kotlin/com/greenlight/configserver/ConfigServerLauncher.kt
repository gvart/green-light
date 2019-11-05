package com.greenlight.configserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@EnableConfigServer
@SpringBootApplication
class ConfigServerLauncher

fun main(args: Array<String>) {
    runApplication<ConfigServerLauncher>(*args)
}