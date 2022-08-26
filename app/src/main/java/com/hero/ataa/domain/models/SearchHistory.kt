package com.hero.ataa.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchHistory(
    @SerialName("history")
    val history: Set<String> = setOf<String>()
)
