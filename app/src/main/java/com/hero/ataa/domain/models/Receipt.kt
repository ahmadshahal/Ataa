package com.hero.ataa.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Receipt(

    @SerialName("name")
    val title: String,

    @SerialName("categories")
    val tags: List<String>,

    @SerialName("ProjectId")
    val projectId: String,

    @SerialName("date")
    val donationDate: String,

    @SerialName("value")
    val donationValue: Int,
)