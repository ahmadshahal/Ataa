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
            delay(2000)
            val adsList = listOf<Ad>(
                Ad(
                    text = "",
                    url = "https://1.bp.blogspot.com/-Zde9ioLE3SY/YWh7qiquXKI/AAAAAAAARCU/m6D-qJJe6QowYPcDWUtb3-YzFGn9xIaUwCLcBGAsYHQ/s0/Android-get-ready-to-sumbit-your-data-safety-secton-social.png",
                ),
                Ad(
                    text = "",
                    url = "https://1.bp.blogspot.com/-4G4zVhAxueg/YKLth6HiL_I/AAAAAAAAQhM/JiTsOudkdXgb94qpNYI66jEGlauS0CETQCLcBGAsYHQ/s0/android-whats-new-in-jetpack-v2.png",
                ),
                Ad(
                    text = "",
                    url = "https://1.bp.blogspot.com/-b1_n6tOHvWU/YKMssWEjo-I/AAAAAAAAQjk/vIJQsAPUpRQKxR44GoCbm3CtRgr8tVBKACLcBGAsYHQ/s0/Android_NewForDevelopers_1024x512_updated.png",
                ),
            )
            emit(DataState.Success(adsList))
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