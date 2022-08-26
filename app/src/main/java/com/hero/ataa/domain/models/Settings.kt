package com.hero.ataa.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Settings(
    @SerialName("dark_mode")
    val darkMode: Boolean = false,

    @SerialName("arabic")
    val arabic: Boolean = true,

    @SerialName("notifications")
    val notifications: Boolean = true,
)
