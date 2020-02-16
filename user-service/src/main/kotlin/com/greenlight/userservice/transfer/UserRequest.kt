package com.greenlight.userservice.transfer

import com.fasterxml.jackson.annotation.JsonFormat
import com.greenlight.userservice.domain.User
import com.greenlight.userservice.domain.UserSex
import java.time.LocalDate
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class UserRequest(
    @get:NotBlank
    val nickName: String,
    @get:NotBlank
    val firstName: String,
    @get:NotBlank
    val lastName: String,
    @get:Email
    val email: String,
    @JsonFormat(pattern = "dd/MM/yyyy")
    val birthDate: LocalDate,
    @get:NotNull
    val sex: UserSex
) : DomainConverter<User> {
    override fun convert(): User {
        return User(null, nickName, firstName, lastName, email, birthDate, sex)
    }
}