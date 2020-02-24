package com.greenlight.common.rsocket.factory

internal enum class RSocketEurekaInstanceProperties(private val serverPort: String) {
    SERVER_PORT("rsocket-port");

    fun value() = serverPort
}