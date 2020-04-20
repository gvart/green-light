package com.greenlight.eventservice.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDate

@Table
data class UserProfile(
    @Id
    var id: String? = null,
    var birthDate: LocalDate,
    var sex: UserSex,
    var image: String
)