package com.hero.ataa.data.remote.models.requests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResendCodeRequest(

    @SerialName("email")
    val email: String,
)