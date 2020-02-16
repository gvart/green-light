package com.greenlight.authservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AuthServiceLauncher

fun main(args: Array<String>) {
    runApplication<AuthServiceLauncher>(*args)
}
