package com.hero.ataa.domain.use_cases

import com.hero.ataa.R
import com.hero.ataa.data.local.repositories.UserRepository
import com.hero.ataa.data.remote.models.requests.VerifyEmailRequest
import com.hero.ataa.data.remote.models.responses.toUser
import com.hero.ataa.data.remote.repositories.AuthRepository
import com.hero.ataa.shared.AtaaException
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiText
import kotlinx.coroutines.flow.flow
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class VerifyUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository,
) {
    operator fun invoke(verifyCode: String, email: String) = flow<DataState<Nothing>> {
        emit(DataState.Loading())
        try {
//            delay(3000)
            val user = authRepository.verifyEmail(VerifyEmailRequest(email = email, verificationCode = verifyCode)).toUser()
            userRepository.update {
                it.copy(name = user.name, email = user.email, token = user.token)
            }
            userRepository.triggerLoggedInValue(true)
            emit(DataState.SuccessWithoutData())
        } catch (ex: UnknownHostException) {
            emit(DataState.Error(UiText.ResourceText(R.string.can_not_reach_the_server)))
        } catch (ex: ConnectException) {
            emit(DataState.Error(UiText.ResourceText(R.string.bad_internet_connection)))
        } catch (ex: SocketTimeoutException) {
            emit(DataState.Error(UiText.ResourceText(R.string.bad_internet_connection)))
        } catch (ex: AtaaException) {
            emit(DataState.Error(UiText.ResourceText(resId = R.string.invalid_code)))
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