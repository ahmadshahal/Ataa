package com.hero.ataa.data.remote.models.responses

import com.hero.ataa.domain.models.Ad
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AdsResponse(
    @SerialName("projects")
    val ads: List<Ad>
)
