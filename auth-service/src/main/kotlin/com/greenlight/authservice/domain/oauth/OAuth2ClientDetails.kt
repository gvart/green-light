package com.greenlight.authservice.domain.oauth

import org.springframework.security.oauth2.provider.ClientDetails
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.Table

@Entity
@Table(name = "client_details")
data class OAuth2ClientDetails(
    @Id
    private val id: String,
    private val clientSecret: String,
    private val resourceIds: String?,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "client_details_scope",
        joinColumns = [JoinColumn(name = "client_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "scope_id", referencedColumnName = "id")]
    )
    private val scope: MutableSet<Scope>,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "client_details_authority",
        joinColumns = [JoinColumn(name = "client_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "authority_id", referencedColumnName = "id")]
    )
    private val authorities: MutableCollection<Authority>,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "client_details_grant_type",
        joinColumns = [JoinColumn(name = "client_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "grant_type_id", referencedColumnName = "id")]
    )
    private val authorizedGrantTypes: MutableSet<GrantType>,
    private var accessTokenValidity: Int,
    private var refreshTokenValidity: Int,
    private val autoApprove: Boolean = true
) : ClientDetails {
    override fun isSecretRequired(): Boolean = false

    override fun getAdditionalInformation(): MutableMap<String, Any> = mutableMapOf()

    override fun getAccessTokenValiditySeconds(): Int = accessTokenValidity

    override fun getResourceIds(): MutableSet<String> = resourceIds?.split(',')?.toMutableSet() ?: mutableSetOf()

    override fun getClientId(): String = id

    override fun isAutoApprove(scope: String?): Boolean = autoApprove

    override fun getAuthorities(): MutableCollection<Authority> = authorities

    override fun getRefreshTokenValiditySeconds(): Int = refreshTokenValidity

    override fun getClientSecret(): String = clientSecret

    override fun getRegisteredRedirectUri(): MutableSet<String> = mutableSetOf()

    override fun isScoped(): Boolean = scope.isNotEmpty()

    override fun getScope(): MutableSet<String> = scope.map { it.name }.toMutableSet()

    override fun getAuthorizedGrantTypes(): MutableSet<String> =
        authorizedGrantTypes.map { it.name }.toMutableSet()
}
