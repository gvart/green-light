package com.greenlight.authservice.repository.oauth

import com.greenlight.authservice.domain.oauth.GrantType
import org.springframework.data.jpa.repository.JpaRepository

interface GrantTypeRepository : JpaRepository<GrantType, Long> {
}