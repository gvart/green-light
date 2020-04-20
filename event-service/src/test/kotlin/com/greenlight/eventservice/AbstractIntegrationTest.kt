package com.greenlight.eventservice

import com.greenlight.eventservice.repository.EventRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.jdbc.JdbcTestUtils
import org.springframework.test.web.reactive.server.WebTestClient
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers


/**
 * Abstraction for Integration tests which instantiate a singleton docker container
 * @see <a href="https://www.testcontainers.org/test_framework_integration/manual_lifecycle_control/#singleton-containers">singleton-container</a>
 * that is shared between all integration tests, bind application context to webClient instance
 * and persist application state.
 *
 *
 * At the end of the test suite the Ryuk container that is started by Testcontainers core will take care of stopping the singleton container.
 * @author gvart
 */
@WithMockUser
@ExtendWith(SpringExtension::class)
@ActiveProfiles("test")
@SpringBootTest(classes = [EventServiceLauncher::class])
class AbstractIntegrationTest {

    lateinit var webClient: WebTestClient

    @Autowired
    lateinit var eventRepository: EventRepository

    @BeforeEach
    fun setup(applicationContext: ApplicationContext) {
        this.webClient = WebTestClient.bindToApplicationContext(applicationContext)
            .configureClient()
            .build()

        eventRepository.deleteAll().subscribe()
    }

    companion object {

        @JvmStatic
        private val postgreSQLContainer = PostgreSQLContainer<Nothing>("postgres:12").apply {
            withUsername("postgres")
            withPassword("postgres")
            withDatabaseName("event_service")

            start()
        }


        @JvmStatic
        @DynamicPropertySource
        fun r2dbcProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.r2dbc.url") { postgreSQLContainer.jdbcUrl.replace("jdbc", "r2dbc") }
            registry.add("spring.r2dbc.username") { "postgres" }
            registry.add("spring.r2dbc.password") { "postgres" }
            registry.add("spring.datasource.url") { postgreSQLContainer.jdbcUrl }
            registry.add("spring.datasource.username") { "postgres" }
            registry.add("spring.datasource.password") { "postgres" }
            registry.add("spring.datasource.type") { "org.postgresql.ds.PGSimpleDataSource" }
        }
    }
}