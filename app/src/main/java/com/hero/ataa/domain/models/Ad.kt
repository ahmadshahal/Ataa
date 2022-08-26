package com.hero.ataa.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ad(
    @SerialName("name")
    val text: String,

    @SerialName("image")
    val url: String?,
)
