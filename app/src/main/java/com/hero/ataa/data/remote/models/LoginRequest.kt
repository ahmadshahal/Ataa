package com.hero.ataa.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(

    @SerialName("email")
    val email: String,

    @SerialName("password")
    val password: String
)
