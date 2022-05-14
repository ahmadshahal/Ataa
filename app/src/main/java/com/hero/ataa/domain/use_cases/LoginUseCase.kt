package com.hero.ataa.domain.use_cases

import com.hero.ataa.R
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class LoginUseCase @Inject constructor() {
    operator fun invoke(email: String, password: String) = flow<DataState<String>> {
        emit(DataState.Loading())
        try {
            delay(3000)
            val token = "123"
            emit(DataState.Success(token))
        } catch (ex: Exception) {
            emit(
                DataState.Error(
                    if (ex.message != null)
                        UiText.DynamicText(ex.message!!)
                    else
                        UiText.ResourceText(R.string.something_went_wrong)
                )
            )
        }
    }
}