package com.hero.ataa.domain.use_cases

import com.hero.ataa.R
import com.hero.ataa.data.remote.repositories.ProjectsRepository
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSacrificeValueUseCase @Inject constructor(
    private val projectsRepository: ProjectsRepository
) {
    operator fun invoke() = flow<DataState<Int>> {
        emit(DataState.Loading())
        try {
            delay(1000)
            val value = projectsRepository.getSacrificeValue().projectValue
            emit(DataState.Success(data = value))
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