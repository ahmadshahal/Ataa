package com.hero.ataa.domain.use_cases

import com.hero.ataa.R
import com.hero.ataa.data.remote.models.RegisterRequest
import com.hero.ataa.data.remote.repositories.AuthRepository
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(email: String, fullName: String, password: String, phoneNumber: String) =
        flow<DataState<Nothing>> {
            emit(DataState.Loading())
            try {
                delay(3000)
                authRepository.register(
                    RegisterRequest(
                        email = email,
                        password = password,
                        fullName = fullName,
                        phoneNumber = phoneNumber,
                    )
                )
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