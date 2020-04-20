package com.greenlight.feedbackservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.ComponentScan

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan("com.greenlight.*")
class FeedbackApplication

fun main(args: Array<String>) {
    runApplication<FeedbackApplication>(*args)
}