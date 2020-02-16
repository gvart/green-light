package com.greenlight.authservice.repository.oauth

import com.greenlight.authservice.domain.oauth.Authority
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorityRepository : JpaRepository<Authority, Long> {
}