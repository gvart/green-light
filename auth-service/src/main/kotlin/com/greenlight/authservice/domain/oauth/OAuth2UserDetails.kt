package com.greenlight.authservice.domain.oauth

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Id
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "user_details")
data class OAuth2UserDetails(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,
    private val username: String,

    @JsonIgnore
    private val password: String,
    private val enabled: Boolean,
    private val locked: Boolean,
    private val expired: Boolean,
    private val failedLoginAttempts: Int,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_details_authority",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "authority_id", referencedColumnName = "id")]
    )
    private val authorities: MutableCollection<Authority>,
    private val createdAt: LocalDate,
    private val updatedAt: LocalDate
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return authorities
    }

    override fun isEnabled(): Boolean {
        return enabled
    }

    override fun getUsername(): String {
        return username
    }

    override fun isCredentialsNonExpired(): Boolean {
        return isEnabled
    }

    override fun getPassword(): String {
        return password
    }

    override fun isAccountNonExpired(): Boolean {
        return !expired
    }

    override fun isAccountNonLocked(): Boolean {
        return !locked
    }
}


