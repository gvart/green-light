package com.greenlight.gateway

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [GatewayLauncher::class])
class ApplicationStartupTests {

    @Test
    fun applicationStarts() {}

}