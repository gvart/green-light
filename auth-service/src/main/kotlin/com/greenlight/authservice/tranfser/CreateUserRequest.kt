package com.greenlight.authservice.tranfser

import com.greenlight.authservice.domain.oauth.OAuth2UserDetails
import com.greenlight.common.transfer.DomainConverter
import java.time.LocalDate
import javax.validation.constraints.NotBlank

data class CreateUserRequest(
    @get:NotBlank
    val username: String

) : DomainConverter<OAuth2UserDetails> {
    override fun convert(): OAuth2UserDetails {
        return OAuth2UserDetails(
            null,
            username,
            "123456",
            enabled = true,
            locked = false,
            expired = false,
            failedLoginAttempts = 0,
            authorities = mutableSetOf(),
            createdAt = LocalDate.now(),
            updatedAt = LocalDate.now()
        )
    }
}