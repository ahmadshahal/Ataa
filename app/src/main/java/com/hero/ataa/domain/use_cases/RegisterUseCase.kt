package com.hero.ataa.domain.use_cases

import com.hero.ataa.R
import com.hero.ataa.data.remote.models.requests.RegisterRequest
import com.hero.ataa.data.remote.repositories.AuthRepository
import com.hero.ataa.shared.AtaaException
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiText
import kotlinx.coroutines.flow.flow
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String, fullName: String, password: String, phoneNumber: String) =
        flow<DataState<Nothing>> {
            emit(DataState.Loading())
            try {
//                delay(3000)
                authRepository.register(
                    RegisterRequest(
                        email = email,
                        password = password,
                        fullName = fullName,
                        phoneNumber = phoneNumber,
                    )
                )
                emit(DataState.SuccessWithoutData())
            } catch (ex: UnknownHostException) {
                emit(DataState.Error(UiText.ResourceText(R.string.can_not_reach_the_server)))
            } catch (ex: ConnectException) {
                emit(DataState.Error(UiText.ResourceText(R.string.bad_internet_connection)))
            } catch (ex: SocketTimeoutException) {
                emit(DataState.Error(UiText.ResourceText(R.string.bad_internet_connection)))
            } catch (ex: AtaaException) {
                emit(DataState.Error(UiText.ResourceText(resId = R.string.email_already_exists)))
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