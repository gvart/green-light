package com.greenlight.authservice.repository.oauth

import com.greenlight.authservice.domain.oauth.OAuth2UserDetails
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface OAuthUserRepository : JpaRepository<OAuth2UserDetails, Long> {
    fun findByUsernameIgnoreCase(username: String): Optional<OAuth2UserDetails>
}