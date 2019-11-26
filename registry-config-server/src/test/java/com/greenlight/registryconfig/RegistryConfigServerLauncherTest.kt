package com.greenlight.registryconfig

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest(classes = [RegistryConfigServerLauncher::class])
class RegistryConfigServerLauncherTest {
    @Test
    fun contextLoads() {
    }
}