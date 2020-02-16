package com.greenlight.authservice.repository.oauth

import com.greenlight.authservice.domain.oauth.OAuth2ClientDetails
import org.springframework.data.jpa.repository.JpaRepository


interface OAuth2ClientDetailsRepository : JpaRepository<OAuth2ClientDetails, String> {
}