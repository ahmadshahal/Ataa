package com.hero.ataa.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class User(
    @SerialName("name")
    val name: String = "",

    @SerialName("email")
    val email: String = "",

    @SerialName("token")
    val token: String = ""
)
