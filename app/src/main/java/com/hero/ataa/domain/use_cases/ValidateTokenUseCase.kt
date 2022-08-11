package com.hero.ataa.domain.use_cases

import com.hero.ataa.R
import com.hero.ataa.data.local.repositories.UserRepository
import com.hero.ataa.data.remote.repositories.AuthRepository
import com.hero.ataa.shared.AtaaException
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiText
import kotlinx.coroutines.flow.flow
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class ValidateTokenUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
) {
    operator fun invoke() = flow<DataState<Nothing>> {
        emit(DataState.Loading())
        try {
            val token = userRepository.user().token
            if (token.isNotEmpty()) {
                authRepository.validateToken(token)
                userRepository.triggerLoggedInValue(true)
            } else {
                userRepository.triggerLoggedInValue(false)
            }
            emit(DataState.SuccessWithoutData())
        } catch (ex: UnknownHostException) {
            userRepository.triggerLoggedInValue(true)
            emit(DataState.Error(UiText.ResourceText(R.string.no_internet_connection)))
        } catch (ex: ConnectException) {
            userRepository.triggerLoggedInValue(true)
            emit(DataState.Error(UiText.ResourceText(R.string.no_internet_connection)))
        } catch (ex: SocketTimeoutException) {
            userRepository.triggerLoggedInValue(true)
            emit(DataState.Error(UiText.ResourceText(R.string.no_internet_connection)))
        } catch (ex: AtaaException) {
            userRepository.triggerLoggedInValue(false)
            userRepository.update {
                it.copy(name = "", email = "", token = "")
            }
            emit(DataState.Error(UiText.DynamicText(ex.message)))
        } catch (ex: Exception) {
            userRepository.triggerLoggedInValue(true)
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