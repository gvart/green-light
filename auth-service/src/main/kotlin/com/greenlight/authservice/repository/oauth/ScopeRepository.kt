package com.greenlight.authservice.repository.oauth

import com.greenlight.authservice.domain.oauth.Scope
import org.springframework.data.jpa.repository.JpaRepository

interface ScopeRepository : JpaRepository<Scope, Long> {
}