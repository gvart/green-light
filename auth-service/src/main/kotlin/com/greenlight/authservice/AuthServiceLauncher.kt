package com.greenlight.authservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableTransactionManagement
@SpringBootApplication
class AuthServiceLauncher

fun main(args: Array<String>) {
    runApplication<AuthServiceLauncher>(*args)
}
