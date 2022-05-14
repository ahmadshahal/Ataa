package com.hero.ataa.domain.use_cases

import com.hero.ataa.shared.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class LoginUseCase @Inject constructor() {
    fun execute(email: String, password: String) = flow<DataState<String>> {
        emit(DataState.Loading())
        try {
            delay(3000)
            val token = "123"
            emit(DataState.Success(token))
        } catch (ex: Exception) {
            // TODO: Fix hardcoded strings.
            emit(DataState.Error(ex.message ?: "حدث خطأ ما"))
        }
    }
}