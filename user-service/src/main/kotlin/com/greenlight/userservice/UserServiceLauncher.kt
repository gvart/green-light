package com.greenlight.userservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.ribbon.RibbonClients
import org.springframework.cloud.netflix.ribbon.eureka.EurekaRibbonClientConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client

@EnableDiscoveryClient
@RibbonClients(defaultConfiguration = [EurekaRibbonClientConfiguration::class])
@SpringBootApplication
@ComponentScan("com.greenlight.*")
class UserServiceLauncher

fun main(args: Array<String>) {
    runApplication<UserServiceLauncher>(*args)
}