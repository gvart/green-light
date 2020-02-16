package com.greenlight.userservice.domain

import javax.persistence.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table("users")
data class User(
    @Id
    var id: Long? = null,
    val nickName: String,
    var firstName: String,
    var lastName: String,
    var email: String,
    val birthDate: LocalDate,
    val sex: UserSex
)