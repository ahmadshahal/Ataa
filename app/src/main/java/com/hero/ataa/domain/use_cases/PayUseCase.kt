package com.hero.ataa.domain.use_cases

import com.hero.ataa.R
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PayUseCase @Inject constructor() {
    operator fun invoke(
        donationValue: String,
        token: String,
        projectId: String
    ) = flow<DataState<String>> {
        emit(DataState.Loading())
        try {
            delay(3000)
            emit(DataState.Success(""))
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