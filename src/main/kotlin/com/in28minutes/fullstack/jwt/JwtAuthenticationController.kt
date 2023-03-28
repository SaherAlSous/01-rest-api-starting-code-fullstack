package com.in28minutes.fullstack.jwt

import com.in28minutes.fullstack.jwt.model.JwtTokenRequest
import com.in28minutes.fullstack.jwt.model.JwtTokenResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class JwtAuthenticationController(
    private val tokenService: JwtTokenService,
    private val authenticationManager: AuthenticationManager
) {
    @PostMapping("/authenticate")
    fun generateToken(
        @RequestBody jwtTokenRequest: JwtTokenRequest
    ): ResponseEntity<JwtTokenResponse> {
        val authenticationToken = UsernamePasswordAuthenticationToken(
            jwtTokenRequest.username,
            jwtTokenRequest.password
        )
        val authentication = authenticationManager.authenticate(authenticationToken)
        val token = tokenService.generateToken(authentication)
        return ResponseEntity.ok(JwtTokenResponse(token))
    }
}