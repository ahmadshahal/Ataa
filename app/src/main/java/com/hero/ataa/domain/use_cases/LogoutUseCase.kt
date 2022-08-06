package com.hero.ataa.domain.use_cases

import com.hero.ataa.R
import com.hero.ataa.data.local.repositories.UserRepository
import com.hero.ataa.shared.AtaaException
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke() = flow<DataState<Nothing>> {
        emit(DataState.Loading())
        try {
//            delay(3000)
            userRepository.update {
                it.copy(name = "", email = "", token = "")
            }
            emit(DataState.SuccessWithoutData())
            delay(100)
            userRepository.triggerLoggedInValue(false)
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