package com.hero.ataa.shared

object Constants {
    object CategoryApiKey {
        const val RAMADAN = "رمضان"
        const val ORPHAN = "أيتام"
        const val HEALTH = "صحة"
        const val MOSQUES = "مساجد"
        const val EDUCATION = "تعليم"
        const val FOOD = "غذاء"
        const val HOUSING = "إسكان"
        const val DRESSING = "كسوة"
        const val SPECIALNEEDS = "ذوي الأحتياجات"
        const val ASSISTANCE = "أغاثة"
    }
    object PermanentProjectId {
        const val SADAKA = "3"
        const val MISKEEN = "1"
        const val SACRIFICE = "4"
        const val ZAKAT = "2"
    }
    object NavArgs {
        const val CATEGORY_KEY = "category"
        const val CATEGORY_API_KEY_KEY = "category_api_key"
        const val PROJECT_KEY = "project"
        const val IS_ARABIC_KEY = "is_arabic"
        const val EMAIL_KEY = "email"
        const val FULL_NAME_KEY = "full_name"
        const val DONATION_VALUE_KEY = "donation_value"
        const val PROJECT_ID_KEY = "project_id"
    }
    object PlacesInSyria {
        val governorates = listOf(
            "دمشق",
            "ريف دمشق",
            "القنيطرة",
            "درعا",
            "السويداء",
            "حمص",
            "طرطوس",
            "اللاذقية",
            "حماة",
            "إدلب",
            "حلب",
            "الرقة",
            "دير الزور",
            "الحسكة",
        )

        val places = mapOf(
            "دمشق" to listOf(
                "منطقة دمشق",
                "منطقة الروضة",
                "منطقة المالكي",
                "منطقة الشعلان",
                "منطقة الصالحية",
                "منطقة الميدان",
                "منطقة برزة",
                "منطقة الشاغور",
                "منطقة القابون",
                "منطقة المزة",
                "منطقة دمر",
                "منطقة القدم",
                "منطقة القنوات",
                "منطقة المهاجرين",
                "منطقة اليرموك",
                "منطقة جوبر",
                "منطقة ركن الدين",
                "منطقة ساروجة",
                "منطقة كفرسوسة",
            ),
            "ريف دمشق" to listOf(
                "منطقة ريف دمشق",
                "منطقة النبك",
                "منطقة القطيفة",
                "منطقة التل",
                "منطقة قدسيا",
                "منطقة الزبداني",
                "منطقة دوما",
                "منطقة داريا",
                "منطقة قطنا",
                "منطقة يبرود",
                "منطقة عربين",
            ),
            "القنيطرة" to listOf(
                "منطقة القنيطرة",
                "منطقة فيق",
            ),
            "درعا" to listOf(
                "منطقة درعا",
                "منطقة الصنمين",
                "منطقة أزرع",
            ),
            "السويداء" to listOf(
                "منطقة السويداء",
                "منطقة شهبا",
            ),
            "حمص" to listOf(
                "منطقة حمص",
                "منطقة المخرم",
                "منطقة القصير",
                "منطقة تدمر",
                "منطقة الرستن",
            ),
            "طرطوس" to listOf(
                "منطقة طرطوس",
                "منطقة الشيخ بدر",
                "منطقة بانياس",
                "منطقة دريكيش",
            ),
            "اللاذقية" to listOf(
                "منطقة اللاذقية",
                "منطقة الحفة",
                "منطقة جبلة",
            ),
            "حماة" to listOf(
                "منطقة حماة",
                "منطقة السلمية",
                "منطقة مصياف",
                "منطقة محردة",
            ),
            "إدلب" to listOf(
                "منطقة إدلب",
                "منطقة أريحا",
                "منطقة معرة النعمان",
            ),
            "حلب" to listOf(
                "منطقة حلب",
                "منطقة أعزاز",
                "منطقة الباب",
                "منطقة الأتارب",
                "منطقة عفرين",
                "منطقة عبن العرب",
            ),
            "الرقة" to listOf(
                "منطقة الرقة",
                "منطقة الثورة",
                "منطقة تل أبيض",
            ),
            "دير الزور" to listOf(
                "منطقة دير الزور",
                "منطقة البوكمال",
                "منطقة الميادين",
            ),
            "الحسكة" to listOf(
                "منطقة الحسكة",
                "منطقة المالكية",
                "منطقة القامشلي",
                "منطقة رأس العين",
            ),
        )
    }
    /**
     * Number of digits of the maximum value allowed.
     */
    const val MAX_MONEY_DONATION = 18
    const val MAX_MISKEEN_PERSONS = 4
    const val MAX_SACRIFICE = 2
    const val NOTIFICATION_CHANNEL_NAME = "Ataa"
}