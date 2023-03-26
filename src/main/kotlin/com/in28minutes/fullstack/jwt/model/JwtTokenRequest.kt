package com.in28minutes.fullstack.jwt.model

data class JwtTokenRequest(
    val username: String,
    val password: String
)
