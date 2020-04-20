package com.greenlight.eventservice.service

import com.greenlight.common.security.service.SecurityService
import com.greenlight.common.service.AbstractCoroutineReadWriteService
import com.greenlight.common.web.error.httperror.NotFoundException
import com.greenlight.eventservice.domain.UserProfile
import com.greenlight.eventservice.repository.UserProfileRepository
import com.greenlight.eventservice.transfer.user.UserProfileRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrElse
import kotlinx.coroutines.reactive.awaitSingle
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.validation.Validator

@Service
class UserProfileService(
    private val userProfileRepository: UserProfileRepository,
    private val securityService: SecurityService,
    validator: Validator
) : AbstractCoroutineReadWriteService<UserProfile, UserProfileRequest, Long>(validator) {

    companion object {
        private val logger = LoggerFactory.getLogger(UserProfileService::class.java)
    }

    override suspend fun saveEntity(userProfileRequest: UserProfileRequest): UserProfile {
        val userId = securityService.extractUserId().awaitSingle()
        val user = userProfileRequest.convert().apply {
            id = userId
        }

        return userProfileRepository.save(user).awaitFirst()
    }

    override suspend fun updateEntity(id: Long, userProfileRequest: UserProfileRequest): UserProfile {
        val user = findOne(id)
        user.apply {
            sex = userProfileRequest.sex
            birthDate = userProfileRequest.birthDate
        }
        return userProfileRepository.save(user).awaitFirst()
    }

    override suspend fun deleteEntity(id: Long) {
        userProfileRepository.deleteById(id).awaitFirst()
    }

    override fun findAllEntities(): Flow<UserProfile> {
        return userProfileRepository.findAll().asFlow()
    }

    override suspend fun findOneEntity(id: Long): UserProfile {
        return userProfileRepository.findById(id)
            .awaitFirstOrElse { throw NotFoundException("User $id not found") }
    }

    override fun getLogger(): Logger {
        return logger
    }
}