package com.hero.ataa.data.remote.models.requests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BeneficiaryRequest(
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

    @SerialName("marital_status")
    val socialStatus: String,

    @SerialName("job")
    val work: String,

    @SerialName("province")
    val governorate: String,

    @SerialName("area")
    val place: String,

    @SerialName("address")
    val address: String,

    @SerialName("residential_status")
    val residentialStatus: String,

    @SerialName("salary")
    val income: String,

    @SerialName("health_status")
    val healthStatus: String,

    @SerialName("description")
    val about: String,

    @SerialName("children_number")
    val kidsNumber: String,
)
