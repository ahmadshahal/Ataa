package com.hero.ataa.domain.use_cases

import com.hero.ataa.R
import com.hero.ataa.domain.models.Ad
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAdsUseCase @Inject constructor() {
    operator fun invoke() = flow<DataState<List<Ad>>> {
        emit(DataState.Loading())
        try {
            delay(20000)
            val adsList = listOf<Ad>(
                Ad(
                    text = "",
                    url = "https://9to5google.com/wp-content/uploads/sites/4/2021/02/android-jetpack-header.png"
                ),
                Ad(
                    text = "",
                    url = "https://www.rtlnieuws.nl/sites/default/files/content/images/2019/08/22/android10.png?itok=85RlBYaw&width=2048&height=1152&impolicy=semi_dynamic"
                ),
                Ad(
                    text = "مبادرة لا للجوع بالتعاون مع جمعية الأيادي البيضاء",
                    url = "https://i.ibb.co/1d7Th9Z/charity.png"
                ),
            )
            emit(DataState.Success(adsList))
        }
        catch (ex: Exception) {
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