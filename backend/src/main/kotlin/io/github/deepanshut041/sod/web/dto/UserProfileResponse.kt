package io.github.deepanshut041.sod.web.dto

data class UserProfileResponse(
        val id: String,
        val name: String,
        val email: String,
        val imgUrl: String
)