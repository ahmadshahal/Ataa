package com.hero.ataa.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EditProfileRequest(

    @SerialName("full_name")
    val fullName: String?,

    @SerialName("old_password")
    val oldPassword: String?,

    @SerialName("password")
    val password: String?
)