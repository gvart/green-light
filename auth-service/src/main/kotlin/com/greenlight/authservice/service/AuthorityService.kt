package com.greenlight.authservice.service

import com.greenlight.authservice.domain.oauth.Authority
import com.greenlight.authservice.repository.oauth.AuthorityRepository
import com.greenlight.common.web.error.httperror.NotFoundException
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class AuthorityService(private val authorityRepository: AuthorityRepository) {

    @Cacheable("authorities")
    fun findOne(name: String): Authority {
        return authorityRepository.findOneByName(name) ?: throw NotFoundException("Could now found authority: $name")
    }

    class DefaultAuthorities {
        companion object {
            const val ADMIN = "ROLE_ADMIN"
            const val USER = "ROLE_USER"
        }
    }
}