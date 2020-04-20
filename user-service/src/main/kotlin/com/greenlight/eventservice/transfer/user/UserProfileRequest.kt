package com.greenlight.eventservice.transfer.user

import com.greenlight.eventservice.domain.UserProfile
import com.greenlight.eventservice.domain.UserSex
import com.greenlight.eventservice.transfer.DomainConverter
import java.time.LocalDate
import javax.validation.constraints.NotNull

data class UserProfileRequest(
    @get:NotNull
    val birthDate: LocalDate,
    @get:NotNull
    val sex: UserSex

) : DomainConverter<UserProfile> {
    override fun convert(): UserProfile {
        return UserProfile(null,  birthDate, sex, "default")
    }
}