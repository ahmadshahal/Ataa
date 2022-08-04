package com.hero.ataa.domain.use_cases

import com.hero.ataa.R
import com.hero.ataa.data.local.repositories.UserRepository
import com.hero.ataa.data.remote.repositories.AuthRepository
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class VerifyUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
) {
    operator fun invoke(verifyCode: String, email: String) = flow<DataState<Nothing>> {
        emit(DataState.Loading())
        try {
            delay(3000)
            val user = authRepository.verifyEmail(verifyCode = verifyCode, email = email)
            userRepository.update {
                it.copy(name = user.name, email = user.email, token = user.token)
            }
            userRepository.triggerLoggedInValue(true)
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