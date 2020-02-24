package com.greenlight.authservice.config.endpoint

class SecurityEndpoints {
    companion object {
        val PERMIT_ALL = arrayOf("/oauth/token", "/actuator/prometheus", "/oauth/check_token")
    }
}