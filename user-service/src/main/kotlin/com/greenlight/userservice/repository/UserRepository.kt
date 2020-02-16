package com.greenlight.userservice.repository

import com.greenlight.userservice.domain.User
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Mono

interface UserRepository : R2dbcRepository<User, Long> {
    @Query("SELECT * from users where nick_name = $1 limit 1")
    fun findByNickName(nickname: String): Mono<User>
}