package com.greenlight.authservice.config

import com.greenlight.authservice.config.endpoint.SecurityEndpoints
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter

@Configuration
@EnableResourceServer
class ResourceServerConfig : ResourceServerConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers(*SecurityEndpoints.PERMIT_ALL).permitAll()
            .anyRequest().authenticated()
    }

}