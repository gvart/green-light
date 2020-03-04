package com.greenlight.common.security.resourceserver

import org.springframework.boot.autoconfigure.security.oauth2.resource.FixedAuthoritiesExtractor
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.client.OAuth2RestOperations
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.OAuth2Request
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices
import java.util.*

/**
 * TODO refactor
 * By default, it designed to return only user details. This class provides [.getRequest] method, which
 * returns clientId and scope of calling service. This information used in controller's security checks.
 */
class CustomUserInfoTokenServices(
    private val userInfoEndpointUrl: String,
    private var restTemplate: OAuth2RestOperations
) :
    ResourceServerTokenServices {

    private val tokenType = DefaultOAuth2AccessToken.BEARER_TYPE
    private val authoritiesExtractor: FixedAuthoritiesExtractor = FixedAuthoritiesExtractor()

    override fun loadAuthentication(accessToken: String): OAuth2Authentication {
        val map = getMap(userInfoEndpointUrl, accessToken)
        if (map.containsKey("error")) {
//            CustomUserInfoTokenServices.log.debug("userinfo returned error: {}", map["error"])
            throw InvalidTokenException(accessToken)
        }
        return extractAuthentication(map)
    }

    private fun extractAuthentication(map: Map<String, Any>): OAuth2Authentication {
        val principal = getPrincipal(map)
        val request = getRequest(map)
        val authorities: List<GrantedAuthority> = authoritiesExtractor
            .extractAuthorities(map)
        val token = UsernamePasswordAuthenticationToken(
            principal, "N/A", authorities
        )
        token.details = map
        return OAuth2Authentication(request, token)
    }

    private fun getPrincipal(map: Map<String, Any>): Any {
        for (key in PRINCIPAL_KEYS) {
            if (map.containsKey(key)) {
                return map[key]!!
            }
        }
        return "unknown"
    }

    private fun getRequest(map: Map<String, Any>): OAuth2Request {
        val request =
            map["oauth2Request"] as Map<String, Any>
        val clientId = request["clientId"] as String
        val scope: List<String> = if (request.containsKey("scope")) request["scope"] as List<String> else emptyList()
        return OAuth2Request(
            null, clientId, null, true, HashSet(scope),
            null, null, null, null
        )
    }

    override fun readAccessToken(accessToken: String): OAuth2AccessToken {
        throw UnsupportedOperationException("Not supported: read access token")
    }

    private fun getMap(
        path: String,
        accessToken: String
    ): MutableMap<String, Any> {
//        CustomUserInfoTokenServices.log.debug("Getting user info from: {}", path)
        return try {
            val existingToken =
                restTemplate.oAuth2ClientContext
                    .accessToken
            if (existingToken == null || accessToken != existingToken.value) {
                val token = DefaultOAuth2AccessToken(
                    accessToken
                )
                token.tokenType = tokenType
                restTemplate.oAuth2ClientContext.accessToken = token
            }


            val a = object : ParameterizedTypeReference<MutableMap<String, Any>>() {}

            return restTemplate.exchange(path, HttpMethod.GET, null, a).body!!
        } catch (ex: Exception) {
            /*    CustomUserInfoTokenServices.log.info(
                    "Could not fetch user details: " + ex.javaClass + ", "
                            + ex.message
                )*/
            Collections.singletonMap<String, Any>(
                "error",
                "Could not fetch user details"
            )
        }
    }

    companion object {
        private val PRINCIPAL_KEYS =
            arrayOf("user", "username", "userid", "user_id", "login", "id", "name")
    }

}