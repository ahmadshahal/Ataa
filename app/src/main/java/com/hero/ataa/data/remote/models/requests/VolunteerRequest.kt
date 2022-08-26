package com.hero.ataa.data.remote.models.requests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class VolunteerRequest(
    @SerialName("full_name")
    val name: String,

    @SerialName("ID_number")
    val nationalNumber: String,

    @SerialName("phone_number")
    val phoneNumber: String,

    @SerialName("email")
    val email: String,

    @SerialName("birth_date")
    val birthDate: String,

    @SerialName("gender")
    val gender: String,

    @SerialName("province")
    val governorate: String,

    @SerialName("area")
    val place: String,

    @SerialName("address")
    val address: String,

    @SerialName("description")
    val about: String,

    @SerialName("job")
    val work: String,
)
