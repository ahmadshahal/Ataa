package com.hero.ataa.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ad(
    @SerialName("text")
    val text: String,

    @SerialName("image_url")
    val url: String,
)
