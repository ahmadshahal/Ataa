package com.hero.ataa.domain.use_cases

import android.content.Context
import com.hero.ataa.R
import com.hero.ataa.data.local.repositories.UserRepository
import com.hero.ataa.data.remote.repositories.AuthRepository
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiText
import com.hero.ataa.system.hasNetwork
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ValidateTokenUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
    @ApplicationContext private val context: Context,
) {
    operator fun invoke() = flow<DataState<Nothing>> {
        emit(DataState.Loading())
        try {
            delay(3000)
            if(context.hasNetwork()) {
                val token = userRepository.user().token
                if(token.isNotEmpty()) {
                    authRepository.validateToken(token)
                    userRepository.triggerLoggedInValue(true)
                }
                else {
                    userRepository.triggerLoggedInValue(false)
                }
            }
            else {
                userRepository.triggerLoggedInValue(true)
            }
            emit(DataState.SuccessWithoutData())
        } catch (ex: Exception) {
            userRepository.update {
                it.copy(name = "", email = "", token = "")
            }
            userRepository.triggerLoggedInValue(false)
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