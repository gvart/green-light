package com.greenlight.authservice.security

import com.greenlight.authservice.domain.oauth.OAuth2UserDetails
import com.greenlight.authservice.repository.oauth.OAuthUserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val OAuthUserRepository: OAuthUserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): OAuth2UserDetails {
        return OAuthUserRepository.findByUsernameIgnoreCase(username)
            .orElseThrow { UsernameNotFoundException(username) }
    }
}