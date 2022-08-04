package com.hero.ataa.domain.use_cases

import com.hero.ataa.R
import com.hero.ataa.data.local.repositories.UserRepository
import com.hero.ataa.data.remote.repositories.ProjectsRepository
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PayUseCase @Inject constructor(
    private val projectsRepository: ProjectsRepository,
    private val userRepository: UserRepository
) {
    operator fun invoke(
        donationValue: String,
        projectId: String
    ) = flow<DataState<String>> {
        emit(DataState.Loading())
        try {
            delay(3000)
            val token = userRepository.user().token
            val url = projectsRepository.pay(token = token, donationValue = donationValue, projectId = projectId)
            emit(DataState.Success(url))
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