package com.hero.ataa

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AtaaApplication : Application() {
    /*
    override fun attachBaseContext(base: Context) {
        val config = Configuration()
        val locale = Locale("ar")
        config.setLocales(LocaleList(locale))
        super.attachBaseContext(base.createConfigurationContext(config))
    }
    */
}