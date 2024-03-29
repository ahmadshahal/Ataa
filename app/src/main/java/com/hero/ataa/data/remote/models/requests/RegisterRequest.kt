package com.hero.ataa.data.remote.models.requests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(

    @SerialName("email")
    val email: String,

    @SerialName("password")
    val password: String,

    @SerialName("full_name")
    val fullName: String,

    @SerialName("phone_number")
    val phoneNumber: String
)