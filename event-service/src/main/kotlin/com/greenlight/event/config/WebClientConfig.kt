package com.greenlight.event.config

import com.greenlight.event.domain.Point
import com.greenlight.event.repository.EventStatusRepository
import com.greenlight.event.service.EventService
import com.greenlight.event.transfer.EventRequest
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.boot.CommandLineRunner
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import java.time.LocalDateTime

@Configuration
class WebClientConfig {

    @LoadBalanced
    fun loadBalancedWebClient() = WebClient.builder()

//    @Bean
//    fun commandLineRunner(eventStatusRepository: EventStatusRepository, eventService: EventService):CommandLineRunner {
//
//        return CommandLineRunner {
//            val convert = EventRequest("title", "description", 1, Point(0.0, 1.1), LocalDateTime.now(), 10)
//            GlobalScope.launch {
//                eventService.save(convert)
//            }
//        }
//    }
}