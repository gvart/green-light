package com.greenlight.userservice

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest(classes = [UserServiceLauncher::class])
class RegistryConfigServerLauncherTest {
    @Test
    fun contextLoads() {
    }
}