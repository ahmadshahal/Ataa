package com.hero.ataa.data.remote.repositories

import com.hero.ataa.data.remote.models.requests.*
import com.hero.ataa.di.BASE_URL
import com.hero.ataa.domain.models.User
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

private const val LOGIN_URL = "/m/login"
private const val REGISTER_URL = "/m/register"
private const val EDIT_PROFILE_URL = "/m/profile"
private const val VERIFY_URL = "/m/register/verify"
private const val RESEND_CODE_URL = "/m/register/code"
private const val VALIDATE_TOKEN_URL = "/m/tokencheck"

class AuthRepository @Inject constructor(
    private val httpClient: HttpClient
) {
    suspend fun login(loginRequest: LoginRequest): User {
        return httpClient.post {
            url("${BASE_URL}${LOGIN_URL}")
            setBody(loginRequest)
        }.body()
    }

    suspend fun register(registerRequest: RegisterRequest) {
        httpClient.post {
            url("${BASE_URL}${REGISTER_URL}")
            setBody(registerRequest)
        }
    }

    suspend fun editProfile(editProfileRequest: EditProfileRequest, token: String): User {
        return httpClient.post {
            url("${BASE_URL}${EDIT_PROFILE_URL}")
            setBody(editProfileRequest)
            header(HttpHeaders.Authorization, "Bearer $token")
        }.body()
    }

    suspend fun verifyEmail(verifyEmailRequest: VerifyEmailRequest): User {
        return httpClient.post {
            url("${BASE_URL}${VERIFY_URL}")
            setBody(verifyEmailRequest)
        }.body()
    }

    suspend fun resendCode(resendCodeRequest: ResendCodeRequest) {
        httpClient.post {
            url("${BASE_URL}${RESEND_CODE_URL}")
            setBody(resendCodeRequest)
        }
    }

    suspend fun validateToken(token: String) {
        httpClient.get {
            url("${BASE_URL}${VALIDATE_TOKEN_URL}")
            header(HttpHeaders.Authorization, "Bearer $token")
        }
    }
}