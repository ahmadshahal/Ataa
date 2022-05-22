package com.hero.ataa.domain.use_cases

import com.hero.ataa.R
import com.hero.ataa.domain.models.Project
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProjectsUseCase @Inject constructor() {
    operator fun invoke(categoryArg: String) = flow<DataState<List<Project>>> {
        emit(DataState.Loading())
        try {
            delay(4000)
            val list = listOf(Project(), Project(), Project(), Project())
            emit(DataState.Success(data = list))
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