package com.greenlight.authservice.service.user

import com.greenlight.authservice.domain.oauth.OAuth2UserDetails
import com.greenlight.authservice.repository.oauth.OAuthUserRepository
import com.greenlight.authservice.service.AuthorityService
import com.greenlight.authservice.tranfser.CreateUserRequest
import com.greenlight.common.service.AbstractReadWriteService
import com.greenlight.common.web.error.httperror.DuplicateEntityException
import com.greenlight.common.web.error.httperror.NotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.transaction.NotSupportedException
import javax.validation.Validator

@Service
class UserService(
    private val userRepository: OAuthUserRepository,
    private val authorityService: AuthorityService,
    validator: Validator
) : AbstractReadWriteService<OAuth2UserDetails, CreateUserRequest, Long>(validator) {
    companion object {
        private val logger = LoggerFactory.getLogger(UserService::class.java)
    }

    private fun existsByUsername(username: String): Boolean {
        return userRepository.findByUsernameIgnoreCase(username) != null
    }

    override fun saveEntity(request: CreateUserRequest): OAuth2UserDetails {
        val authority = authorityService.findOne(AuthorityService.DefaultAuthorities.USER)
        val user = request.convert().apply {
            this.authorities.add(authority)
        }

        if (existsByUsername(request.username)) {
            throw DuplicateEntityException("username", request.username)
        }

        return userRepository.save(user)
    }

    override fun updateEntity(id: Long, request: CreateUserRequest): OAuth2UserDetails {
        throw NotSupportedException("Could not update user")
    }

    override fun deleteEntity(id: Long) {
        userRepository.deleteById(id)
    }

    override fun findAllEntities(): List<OAuth2UserDetails> {
        return userRepository.findAll()
    }

    override fun findOneEntity(id: Long): OAuth2UserDetails {
        return userRepository.findById(id).orElseThrow { NotFoundException("User $id not found.") }
    }

    override fun getLogger(): Logger {
        return logger
    }
}