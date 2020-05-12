package io.github.deepanshut041.sod.domain.service

import io.github.deepanshut041.sod.data.entity.UserEntityMapper
import io.github.deepanshut041.sod.data.repository.UserRepository
import io.github.deepanshut041.sod.domain.model.UserModel
import io.github.deepanshut041.sod.util.ResourceNotFoundException
import io.github.deepanshut041.sod.auth.util.UserPrincipal
import io.github.deepanshut041.sod.util.toNullable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service("userService")
class UserServiceImpl(
        @Autowired val userRepository: UserRepository
): UserService {

    @Throws(UsernameNotFoundException::class)
    override fun getUserById(id: String): UserDetails? {
        userRepository.findById(id).map(UserEntityMapper::to).toNullable()?.let {
            return UserPrincipal.create(it, emptyMap(), rolesToAuthority(it.roles))
        }
        throw ResourceNotFoundException("User", "id", id)
    }

    @Throws(UsernameNotFoundException::class)
    override fun getUserByEmail(email: String): UserDetails? {
        userRepository.findByEmail(email).map(UserEntityMapper::to).toNullable()?.let {
            return UserPrincipal.create(it, emptyMap(), rolesToAuthority(it.roles))
        }
        throw ResourceNotFoundException("User", "email", email)
    }

    override fun saveUser(model: UserModel): UserModel {
        return UserEntityMapper.to(userRepository.save(UserEntityMapper.from(model)))
    }

    override fun existsByEmail(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    override fun loadUserByUsername(username: String): UserDetails? {
        return getUserByEmail(username)
    }

}

interface UserService: UserDetailsService {
    fun getUserById(id: String): UserDetails?
    fun getUserByEmail(email: String): UserDetails?
    fun saveUser(model: UserModel): UserModel
    fun existsByEmail(email: String): Boolean
}

fun rolesToAuthority(roles: List<String>): Collection<GrantedAuthority>{
    val authorises = ArrayList<GrantedAuthority>()

    roles.map {
        authorises.add(SimpleGrantedAuthority(it))
    }
    return authorises
}