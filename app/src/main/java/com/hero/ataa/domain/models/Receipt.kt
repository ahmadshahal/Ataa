package com.hero.ataa.domain.models


data class Receipt(
    val title: String = "بناء وتأثيث مدرسة ابتدائية بمساحة 5500 م2 لتعليم ذوي الاحتياجات الخاصة",
    val tags: List<String> = listOf("تعليم", "ذوي الهمم"),
    val projectId: String = "#tKt2341",
    val donationDate: String = "1/1/2022",
    val donationValue: Int = 200000,
    val cardCode: String = "1374",
)