package io.github.deepanshut041.sod.data.entity

import io.github.deepanshut041.sod.domain.model.enums.AuthProvider
import io.github.deepanshut041.sod.domain.model.UserModel
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("users")
data class UserEntity(@Id val id: String? = null,
                      val name: String,
                      @Indexed(unique = true) val email: String,
                      val password: String,
                      val imgUrl: String,
                      val emailVerified: Boolean = false,
                      val accountLocked: Boolean = false,
                      val accountExpired: Boolean = false,
                      val credentialsExpired: Boolean = false,
                      val provider: AuthProvider,
                      val providerId: String,
                      val roles: List<String> = emptyList(),
                      val createdAt:Date,
                      val updatedAt: Date
                      )

object UserEntityMapper {
    fun from(user: UserModel): UserEntity = UserEntity(
            id = user.id,
            email = user.email,
            name = user.name,
            password = user.password,
            emailVerified = user.emailVerified,
            accountLocked = user.accountLocked,
            accountExpired = user.accountExpired,
            credentialsExpired = user.credentialsExpired,
            provider = user.provider,
            providerId = user.providerId,
            roles = user.roles,
            createdAt = user.createdAt,
            updatedAt = user.updateAt,
            imgUrl = user.imgUrl
    )

    fun to(user: UserEntity): UserModel = UserModel(
            id = user.id,
            email = user.email,
            name = user.name,
            password = user.password,
            emailVerified = user.emailVerified,
            accountLocked = user.accountLocked,
            accountExpired = user.accountExpired,
            credentialsExpired = user.credentialsExpired,
            provider = user.provider,
            providerId = user.providerId,
            roles = user.roles,
            createdAt = user.createdAt,
            updateAt = user.updatedAt,
            imgUrl = user.imgUrl
    )
}