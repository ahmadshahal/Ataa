package com.hero.ataa.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PayRequest(

    @SerialName("projectId")
    val projectId: Int,

    @SerialName("value")
    val value: String,
)