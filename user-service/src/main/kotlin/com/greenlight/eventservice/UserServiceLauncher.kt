package com.greenlight.eventservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.ComponentScan

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan("com.greenlight.*")
class UserServiceLauncher

fun main(args: Array<String>) {
    runApplication<UserServiceLauncher>(*args)
}