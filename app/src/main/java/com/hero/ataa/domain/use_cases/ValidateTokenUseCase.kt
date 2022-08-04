package com.hero.ataa.domain.use_cases

import com.hero.ataa.R
import com.hero.ataa.data.local.repositories.UserRepository
import com.hero.ataa.data.remote.repositories.AuthRepository
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ValidateTokenUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
) {
    operator fun invoke() = flow<DataState<Nothing>> {
        emit(DataState.Loading())
        try {
            delay(3000)

            // TODO
            // No Internet -> Success
            // Invalid Token -> Delete User

            // userRepository.user().token
            val success = userRepository.user().token.isNotEmpty()
            if(success) {
                userRepository.triggerLoggedInValue(true)
            }
            else {
                userRepository.triggerLoggedInValue(false)
                userRepository.update {
                    it.copy(name = "", email = "", token = "")
                }
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