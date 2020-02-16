package com.greenlight.userservice.service

import com.greenlight.common.web.error.httperror.NotFoundException
import com.greenlight.common.web.error.httperror.ValidationException
import com.greenlight.common.web.handler.service.ReadWriteService
import com.greenlight.userservice.domain.User
import com.greenlight.userservice.repository.UserRepository
import com.greenlight.userservice.transfer.UserRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrElse
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) : ReadWriteService<User, UserRequest, Long> {
    override suspend fun save(request: UserRequest): User {
        val user = request.convert()

        if (nickNameExists(request.nickName)) {
            throw ValidationException("Validation Exception", mapOf(Pair("nickName", "Nickname already exists")))
        }

        return userRepository.save(user).awaitFirst()
    }

    private suspend fun nickNameExists(nickName: String): Boolean {
        return userRepository.findByNickName(nickName).awaitFirstOrNull() != null
    }

    override suspend fun update(id: Long, request: UserRequest): User {
        val user = findOne(id)

        user.apply {
            firstName = request.firstName
            lastName = request.lastName
            email = request.email
        }

        return userRepository.save(user).awaitFirst()
    }

    override suspend fun delete(id: Long): Void {
        //todo remove cascade all entities related to this user
        return userRepository.deleteById(id).awaitFirst()
    }

    override fun findAll(): Flow<User> {
        return userRepository.findAll().asFlow()
    }

    override suspend fun findOne(id: Long): User {
        return userRepository.findById(id).awaitFirstOrElse { throw NotFoundException("User $id not found") }
    }
}