package com.hero.ataa.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Receipt(

    @SerialName("title")
    val title: String = "بناء وتأثيث مدرسة ابتدائية بمساحة 5500 م2 لتعليم ذوي الاحتياجات الخاصة",

    @SerialName("tags")
    val tags: List<String> = listOf("تعليم", "ذوي الهمم"),

    @SerialName("project_id")
    val projectId: String = "#tKt2341",

    @SerialName("donation_date")
    val donationDate: String = "1/1/2022",

    @SerialName("donation_value")
    val donationValue: Int = 200000,
)