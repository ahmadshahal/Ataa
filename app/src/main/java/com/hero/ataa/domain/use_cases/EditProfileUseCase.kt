package com.hero.ataa.domain.use_cases

import com.hero.ataa.R
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


// TODO.
// This UseCase returns User.

class EditProfileUseCase @Inject constructor() {
    operator fun invoke(
        fullName: String? = null,
        oldPassword: String? = null,
        password: String? = null,
    ) = flow<DataState<String>> {
        emit(DataState.Loading())
        try {
            delay(3000)
            // TODO: Returns User.
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