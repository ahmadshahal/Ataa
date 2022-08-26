package com.hero.ataa.data.remote.models.responses

import com.hero.ataa.domain.models.Receipt
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReceiptsResponse(
    @SerialName("user_donations")
    val receipts: List<Receipt>
)
