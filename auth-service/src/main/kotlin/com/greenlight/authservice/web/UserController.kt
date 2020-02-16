package com.greenlight.authservice.web

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/oauth/users")
class UserController {

    @GetMapping("/current")
    fun get(principal: Principal): ResponseEntity<Principal> {
        return ResponseEntity.ok(principal)
    }
}