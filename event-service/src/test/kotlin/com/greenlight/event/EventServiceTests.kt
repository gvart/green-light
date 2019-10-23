package com.greenlight.event

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers(disabledWithoutDocker = true)
@SpringBootTest
class EventServiceTests {

    @Container
    val container: GenericContainer<*> = GenericContainer<Nothing>().apply {
        dockerImageName = "postgres:12"
        exposedPorts = listOf(5432)
        withEnv(
            mapOf(
                "POSTGRES_PASSWORD" to "postgres",
                "POSTGRES_DB" to "postgres"
            )
        )
    }

    @Test
    fun contextLoads() {}
}
