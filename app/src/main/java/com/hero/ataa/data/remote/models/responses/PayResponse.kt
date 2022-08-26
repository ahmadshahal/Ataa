package com.hero.ataa.data.remote.models.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PayResponse(

    @SerialName("url")
    val url: String,
)