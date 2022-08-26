package com.hero.ataa.domain.use_cases

import com.hero.ataa.R
import com.hero.ataa.data.remote.repositories.ProjectsRepository
import com.hero.ataa.domain.models.Ad
import com.hero.ataa.shared.AtaaException
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiText
import kotlinx.coroutines.flow.flow
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class GetAdsUseCase @Inject constructor(
    private val projectsRepository: ProjectsRepository
) {
    operator fun invoke() = flow<DataState<List<Ad>>> {
        emit(DataState.Loading())
        try {
//            delay(2000)
            val adsList = projectsRepository.getAds().ads
            emit(DataState.Success(adsList))
        } catch (ex: UnknownHostException) {
            emit(DataState.Error(UiText.ResourceText(R.string.can_not_reach_the_server)))
        } catch (ex: ConnectException) {
            emit(DataState.Error(UiText.ResourceText(R.string.bad_internet_connection)))
        } catch (ex: SocketTimeoutException) {
            emit(DataState.Error(UiText.ResourceText(R.string.bad_internet_connection)))
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