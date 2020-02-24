package com.greenlight.common.security

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.core.io.Resource

@ConstructorBinding
@ConfigurationProperties(prefix = "greenlight.security.oauth2.jwt")
data class JwtProperties(
    val keyStore: Resource,
    val keyStorePassword: String,
    val keyPairAlias: String,
    val keyPairPassword: String,
    val publicKey: Resource
)