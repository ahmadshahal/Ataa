package com.hero.ataa.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("full_Name")
    val name: String = "Ahmad",

    @SerialName("email")
    val email: String = "ahmad.alshahal@gmail.com",

    @SerialName("token")
    val token: String = "bearer 1231234314"
)
