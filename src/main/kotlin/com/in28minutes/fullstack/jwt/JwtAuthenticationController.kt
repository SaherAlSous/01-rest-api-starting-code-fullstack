package com.in28minutes.fullstack.jwt

import com.in28minutes.fullstack.jwt.model.JwtTokenRequest
import com.in28minutes.fullstack.jwt.model.JwtTokenResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
    name = "JWT Generator",
    value = ["/"]
)
class JwtAuthenticationController(
    private val jwtTokenService: JwtTokenService,
    private val authenticationManager: AuthenticationManager
) {

    @PostMapping(
        name = "Generate JWT Token",
        value = ["authenticate"])
    fun getToken(
        @RequestBody jwtTokenRequest: JwtTokenRequest
    ): ResponseEntity<JwtTokenResponse> {
        val authenticationToken =
            UsernamePasswordAuthenticationToken(
                jwtTokenRequest.username,
                jwtTokenRequest.password
            )
        val authentication =
            authenticationManager
                .authenticate(authenticationToken)

        val token = jwtTokenService.generateToken(authentication)

        return ResponseEntity.ok(JwtTokenResponse(token))
    }

}