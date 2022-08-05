package com.hero.ataa.domain.use_cases

import com.hero.ataa.R
import com.hero.ataa.data.local.repositories.UserRepository
import com.hero.ataa.data.remote.models.EditProfileRequest
import com.hero.ataa.data.remote.repositories.AuthRepository
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class EditProfileUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository
) {
    operator fun invoke(
        fullName: String? = null,
        oldPassword: String? = null,
        password: String? = null,
    ) = flow<DataState<Nothing>> {
        emit(DataState.Loading())
        try {
            delay(3000)
            val user = authRepository.editProfile(
                editProfileRequest = EditProfileRequest(
                    fullName = fullName,
                    oldPassword = oldPassword,
                    password = password
                ),
                token = userRepository.user().token
            )
            userRepository.update {
                it.copy(name = user.name, email = user.email, token = user.token)
            }
            emit(DataState.SuccessWithoutData())
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