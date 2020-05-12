package io.github.deepanshut041.sod.web.controller

import io.github.deepanshut041.sod.auth.misc.TokenProvider
import io.github.deepanshut041.sod.auth.util.UserPrincipal
import io.github.deepanshut041.sod.domain.model.UserModel
import io.github.deepanshut041.sod.domain.model.enums.AuthProvider
import io.github.deepanshut041.sod.domain.service.UserService
import io.github.deepanshut041.sod.util.ApiResponse
import io.github.deepanshut041.sod.util.BadRequestException
import io.github.deepanshut041.sod.web.dto.AuthResponse
import io.github.deepanshut041.sod.web.dto.LoginRequest
import io.github.deepanshut041.sod.web.dto.SignUpRequest
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.validation.Valid


@RestController
@RequestMapping(AccountController.AUTH_BASE_URI, consumes = ["application/json"])
@Tag(name = "Account Api", description = " This contains url related to user account")
class AccountController(
        @Autowired private val authenticationManager: AuthenticationManager,
        @Autowired private val userService: UserService,
        @Autowired private val passwordEncoder: PasswordEncoder,
        @Autowired private val tokenProvider: TokenProvider
) {

    @PostMapping("/login")
    fun authenticateUser(@Valid @RequestBody loginRequest: LoginRequest): ResponseEntity<AuthResponse>? {
        val authentication: Authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                        loginRequest.email,
                        loginRequest.password
                )
        )
        SecurityContextHolder.getContext().authentication = authentication
        val token = tokenProvider.createToken(authentication)
        val userPrincipal = authentication.principal as UserPrincipal

        return ResponseEntity.ok(AuthResponse(accessToken = token, name = userPrincipal.mName, email = userPrincipal.mEmail, imageUrl = userPrincipal.mImgUrl, id = userPrincipal.id))
    }

    @PostMapping("/register")
    fun registerUser(@Valid @RequestBody signUpRequest: SignUpRequest): ResponseEntity<ApiResponse>? {
        if (userService.existsByEmail(signUpRequest.email)) {
            throw BadRequestException("Email address already in use.")
        }
        // Creating user's account
        val user = UserModel(
                name = signUpRequest.name,
                email = signUpRequest.email,
                password = passwordEncoder.encode(signUpRequest.password),
                imgUrl = "",
                roles = listOf("USER_ROLE"),
                providerId = "",
                provider = AuthProvider.local,
                createdAt = Date(),
                updateAt = Date()
        )

        val result = userService.saveUser(user)
        return ResponseEntity.ok(ApiResponse(true, "User registered successfully"))
    }


    companion object {
        const val AUTH_BASE_URI = "/api/account"
    }
}