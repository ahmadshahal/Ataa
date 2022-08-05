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
    /**
     * Number of digits of the maximum value allowed.
     */
    const val MAX_MONEY_DONATION = 18
    const val MAX_MISKEEN_PERSONS = 4
    const val MAX_SACRIFICE = 2
    const val NOTIFICATION_CHANNEL_NAME = "Ataa"
}