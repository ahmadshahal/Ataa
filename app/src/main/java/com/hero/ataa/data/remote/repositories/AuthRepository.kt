package com.hero.ataa.data.remote.repositories

import com.hero.ataa.data.remote.models.requests.*
import com.hero.ataa.data.remote.models.responses.NetworkUser
import com.hero.ataa.data.remote.models.responses.UserResponse
import io.ktor.client.*
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
    suspend fun login(loginRequest: LoginRequest): UserResponse {
        /*
        return httpClient.post {
            url("${BASE_API_URL}${LOGIN_URL}")
            setBody(loginRequest)
        }.body()
         */
        return UserResponse(
            user = NetworkUser(
                email = "ahmad@gmail.com",
                name = "Ahmad Shahal",
            ),
            token = "1234"
        )
    }

    suspend fun register(registerRequest: RegisterRequest) {
        /*
        httpClient.post {
            url("${BASE_API_URL}${REGISTER_URL}")
            setBody(registerRequest)
        }
         */
    }

    suspend fun editProfile(editProfileRequest: EditProfileRequest, token: String): UserResponse {
        /*
        return httpClient.post {
            url("${BASE_API_URL}${EDIT_PROFILE_URL}")
            setBody(editProfileRequest)
            header(HttpHeaders.Authorization, "Bearer $token")
        }.body()
         */
        return UserResponse(
            user = NetworkUser(
                email = "ahmad@gmail.com",
                name = "Ahmad Shahal",
            ),
            token = "1234"
        )
    }

    suspend fun verifyEmail(verifyEmailRequest: VerifyEmailRequest): UserResponse {
        /*
        return httpClient.post {
            url("${BASE_API_URL}${VERIFY_URL}")
            setBody(verifyEmailRequest)
        }.body()
         */
        return UserResponse(
            user = NetworkUser(
                email = "ahmad@gmail.com",
                name = "Ahmad Shahal",
            ),
            token = "1234"
        )
    }

    suspend fun resendCode(resendCodeRequest: ResendCodeRequest) {
        /*
        httpClient.post {
            url("${BASE_API_URL}${RESEND_CODE_URL}")
            setBody(resendCodeRequest)
        }
         */
    }

    suspend fun validateToken(token: String) {
        /*
        httpClient.get {
            url("${BASE_API_URL}${VALIDATE_TOKEN_URL}")
            header(HttpHeaders.Authorization, "Bearer $token")
        }
         */
    }
}