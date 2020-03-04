package com.greenlight.common.security.domain.oauth

data class UserPrincipal(
    private val id: Long,
    private val username: String,

    private val enabled: Boolean,
    private val locked: Boolean,
    private val expired: Boolean

)

