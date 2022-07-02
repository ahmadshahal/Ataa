package com.hero.ataa.shared

object Constants {
    object CategoryApiKey {
        // TODO: Update with the API.
        const val RAMADAN = "ramadan"
        const val ORPHAN = "orphan"
        const val HEALTH = "health"
        const val MOSQUES = "mosques"
        const val EDUCATION = "education"
        const val FOOD = "food"
        const val HOUSING = "housing"
        const val DRESSING = "housing"
        const val SPECIALNEEDS = "special needs"
        const val ASSISTANCE = "assistance"
    }
    object PermanentProjectApiKey {
        // TODO: Update with the API.
        const val SADAKA = "sadaka"
        const val MISKEEN = "miskeen"
        const val SACRIFICE = "sacrifice"
        const val KAFFARA = "kaffara"
        const val ZAKAT = "zakat"
    }
    object NavArgs {
        const val CATEGORY_KEY = "category"
        const val CATEGORY_API_KEY_KEY = "category_api_key"
        const val PROJECT_KEY = "project"
        const val IS_ARABIC_KEY = "is_arabic"
    }
    const val MAX_MONEY_DONATION = 18
    const val MAX_MISKEEN_PERSONS = 4
}