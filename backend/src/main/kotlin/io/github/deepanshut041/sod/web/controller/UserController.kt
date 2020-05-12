package io.github.deepanshut041.sod.web.controller

import io.github.deepanshut041.sod.auth.util.UserPrincipal
import io.github.deepanshut041.sod.web.controller.UserController.Companion.USER_BASE_URI
import io.github.deepanshut041.sod.web.dto.UserProfileResponse
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(USER_BASE_URI, consumes = ["application/json"])
@Tag(name = "User Api", description = "This contains url related to user account")
class UserController {

    @GetMapping("/me")
    @SecurityRequirement(name = "bearerAuth")
    fun getMyProfile(): ResponseEntity<UserProfileResponse> {
        val user = SecurityContextHolder.getContext().authentication.principal as UserPrincipal
        return ResponseEntity.ok(UserProfileResponse(id = user.id, name = user.name, email = user.mEmail, imgUrl = user.mImgUrl))
    }

    companion object {
        const val USER_BASE_URI = "/api/users"
    }
}