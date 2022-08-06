package com.hero.ataa.data.remote.models.responses

import com.hero.ataa.domain.models.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("user")
    val user: NetworkUser,

    @SerialName("token")
    val token: String
)

fun UserResponse.toUser() = User(email = user.email, name = user.name, token = token)

@Serializable
data class NetworkUser(
    @SerialName("full_name")
    val name: String,

    @SerialName("email")
    val email: String
)