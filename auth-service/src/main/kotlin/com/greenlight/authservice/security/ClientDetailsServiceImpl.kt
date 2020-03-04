package com.greenlight.authservice.security

import com.greenlight.authservice.domain.oauth.OAuth2ClientDetails
import com.greenlight.authservice.repository.oauth.OAuth2ClientDetailsRepository
import org.springframework.context.annotation.Primary
import org.springframework.security.oauth2.provider.ClientDetailsService
import org.springframework.security.oauth2.provider.ClientRegistrationException
import org.springframework.stereotype.Service

@Service
@Primary
class ClientDetailsServiceImpl(private val repositoryOAuth2Details: OAuth2ClientDetailsRepository) :
    ClientDetailsService {
    override fun loadClientByClientId(clientId: String): OAuth2ClientDetails {
        return repositoryOAuth2Details.findById(clientId)
            .orElseThrow { ClientRegistrationException("Client $clientId not found") }
    }
}