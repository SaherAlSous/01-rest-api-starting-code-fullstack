package com.in28minutes.fullstack.jwt

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.stream.Collectors


@Service
class JwtTokenService(
    private val jwtEncoder: JwtEncoder
) {
    fun generateToken(authentication: Authentication): String {
        val scope = authentication
            .authorities
            .stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(" "));

        val claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plus(90, ChronoUnit.MINUTES))
            .subject(authentication.name)
            .claim("scope", scope)
            .build()

        return jwtEncoder
            .encode(JwtEncoderParameters.from(claims))
            .tokenValue
    }

}