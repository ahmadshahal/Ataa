package com.hero.ataa.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResendCodeRequest(

    @SerialName("email")
    val email: String,
)