package com.hero.ataa.domain.models

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchHistory(
    @SerialName("history")
    val history: PersistentList<String> = persistentListOf()
)
