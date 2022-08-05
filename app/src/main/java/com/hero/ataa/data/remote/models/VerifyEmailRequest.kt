package com.hero.ataa.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VerifyEmailRequest(

    @SerialName("email")
    val email: String,

    @SerialName("code")
    val verificationCode: String,
)