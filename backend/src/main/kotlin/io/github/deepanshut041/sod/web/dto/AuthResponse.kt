package io.github.deepanshut041.sod.web.dto

data class AuthResponse(
        val accessToken: String,
        val tokenType: String = "Bearer ",
        val name: String,
        val email: String,
        val imageUrl: String,
        val id: String
)