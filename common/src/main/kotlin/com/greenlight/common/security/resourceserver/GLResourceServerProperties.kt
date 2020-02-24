package com.greenlight.common.security.resourceserver

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "greenlight.oauth2.resource")
data class GLResourceServerProperties(val autoConfigure: Boolean = false)
