package com.hero.ataa.data.remote.repositories

import com.hero.ataa.domain.models.User
import javax.inject.Inject

class AuthRepository @Inject constructor() {
    fun login(email: String, password: String): User {
        return User()
    }

    fun register(email: String, password: String, fullName: String, phoneNumber: String) {}

    fun logout() {}

    fun editProfile(name: String? = null, password: String? = null, oldPassword: String? = null): User {
        return User()
    }

    fun verifyEmail(verifyCode: String, email: String): User {
        return User()
    }

    fun resendCode(email: String) {}

    // TODO.
    fun validateToken(token: String) {}
}