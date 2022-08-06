package com.hero.ataa.domain.use_cases

import com.hero.ataa.R
import com.hero.ataa.data.remote.models.requests.ResendCodeRequest
import com.hero.ataa.data.remote.repositories.AuthRepository
import com.hero.ataa.shared.AtaaException
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiText
import kotlinx.coroutines.flow.flow
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class ResendCodeUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String) = flow<DataState<String>> {
        emit(DataState.Loading())
        try {
//            delay(3000)
            authRepository.resendCode(ResendCodeRequest(email = email))
            emit(DataState.SuccessWithoutData())
        } catch (ex: UnknownHostException) {
            emit(DataState.Error(UiText.ResourceText(R.string.no_internet_connection)))
        } catch (ex: ConnectException) {
            emit(DataState.Error(UiText.ResourceText(R.string.no_internet_connection)))
        } catch (ex: SocketTimeoutException) {
            emit(DataState.Error(UiText.ResourceText(R.string.no_internet_connection)))
        } catch (ex: AtaaException) {
            emit(DataState.Error(UiText.DynamicText(ex.message)))
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