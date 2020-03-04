package com.greenlight.userservice.service

import com.greenlight.common.service.AbstractCoroutineReadWriteService
import com.greenlight.common.web.error.httperror.DuplicateEntityException
import com.greenlight.common.web.error.httperror.NotFoundException
import com.greenlight.userservice.domain.User
import com.greenlight.userservice.reactive.service.AuthServiceClient
import com.greenlight.userservice.repository.UserRepository
import com.greenlight.userservice.transfer.user.UserAuthRequest
import com.greenlight.userservice.transfer.user.UserRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrElse
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactive.awaitSingle
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.validation.Validator

@Service
class UserService(
    private val userRepository: UserRepository,
    private val authServiceClient: AuthServiceClient,
    validator: Validator
) : AbstractCoroutineReadWriteService<User, UserRequest, Long>(validator) {

    companion object {
        private val logger = LoggerFactory.getLogger(UserService::class.java)
    }

    override suspend fun saveEntity(request: UserRequest): User {
        val user = request.convert()

        if (nickNameExists(user.nickName)) {
            throw DuplicateEntityException("username", user.nickName)
        }

        if (emailAddressExists(user.email)) {
            throw DuplicateEntityException("email", user.email)
        }

        authServiceClient.createUserAccount(UserAuthRequest(request.username)).doOnError { throw it }.awaitSingle()


        return userRepository.save(user).awaitFirst()
    }

    private suspend fun nickNameExists(nickName: String): Boolean {
        return userRepository.findByNickName(nickName).awaitFirstOrNull() != null
    }

    private suspend fun emailAddressExists(email: String): Boolean {
        return userRepository.findByEmailAddress(email).awaitFirstOrNull() != null
    }

    override suspend fun updateEntity(id: Long, request: UserRequest): User {
        val user = findOne(id)

        user.apply {
            firstName = request.firstName
            lastName = request.lastName
            email = request.email
        }

        return userRepository.save(user).awaitFirst()
    }

    override suspend fun deleteEntity(id: Long) {
        //todo remove cascade all entities related to this user
        userRepository.deleteById(id).awaitFirst()
    }

    override fun findAllEntities(): Flow<User> {
        return userRepository.findAll().asFlow()
    }

    override suspend fun findOneEntity(id: Long): User {
        return userRepository.findById(id)
            .awaitFirstOrElse { throw NotFoundException("User $id not found") }
    }

    override fun getLogger(): Logger {
        return logger
    }
}