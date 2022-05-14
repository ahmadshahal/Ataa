package com.hero.ataa.utils

import com.hero.ataa.R
import android.util.Patterns

object Validation {
    fun validateEmail(email: String) : Int? {
        if(email.isEmpty()) {
            return R.string.cant_be_empty
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return R.string.invalid_email
        }
        return null
    }

    fun validateLoginPassword(password: String) : Int? {
        if(password.isEmpty()) {
            return R.string.cant_be_empty
        }
        return null
    }

}