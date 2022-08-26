package com.hero.ataa.shared

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiText {
    data class DynamicText(val text: String) : UiText()
    data class ResourceText(@StringRes val resId: Int) : UiText()

    @Composable
    fun asString(): String {
        return when(this) {
            is DynamicText -> this.text
            is ResourceText -> stringResource(id = this.resId)
        }
    }

    fun asString(context: Context): String {
        return when(this) {
            is DynamicText -> this.text
            is ResourceText -> context.getString(this.resId)
        }
    }
}
