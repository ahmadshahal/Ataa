package com.hero.ataa.data.remote.models.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProjectValueResponse(
    @SerialName("value")
    val projectValue: Int,
)
