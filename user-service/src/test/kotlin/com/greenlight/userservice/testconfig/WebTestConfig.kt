package com.greenlight.userservice.testconfig

import com.greenlight.userservice.config.RouterConfig
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Import

@TestConfiguration
@Import(SecurityTestConfig::class, RouterConfig::class)
class WebTestConfig