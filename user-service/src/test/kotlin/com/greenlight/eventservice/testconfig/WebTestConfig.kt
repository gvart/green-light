package com.greenlight.eventservice.testconfig

import com.greenlight.eventservice.config.RouterConfig
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Import

@TestConfiguration
@Import(SecurityTestConfig::class, RouterConfig::class)
class WebTestConfig