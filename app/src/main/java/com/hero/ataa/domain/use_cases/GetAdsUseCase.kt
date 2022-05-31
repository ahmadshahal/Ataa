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
            delay(5000)
            val adsList = listOf<Ad>(
                Ad(
                    text = "",
                    url = "https://1.bp.blogspot.com/-b6bxqJmHSBQ/YCF7iAb1e8I/AAAAAAAAQD4/bjVGymoEwg4HKkjQI04cj9LyYnHS4LhdQCLcBGAsYHQ/s0/new-curriculum-for-android-educators-social-v4.png"
                ),
                Ad(
                    text = "",
                    url = "https://lh5.googleusercontent.com/XDdAsraMMs0efpmGpsO8onzoHORvkJH2YTFBBQPdy3EEL4kzJqNafyYyl4fSPRObqDrI9kXHJ2ydOqt68pCEVXzq07X8flEbjtBkLdcDEtgqlIxgfJSfepbbKZfH2JCN5znZSyFgaA=w1200-h630-p-k-no-nu"
                ),
                Ad(
                    text = "",
                    url = "https://1.bp.blogspot.com/-IoW_neqnD9E/YZ6t6ijCy_I/AAAAAAAARMY/Ltbtxm_0fQc_Vc1PLyC8dQgypnp6e9ufwCLcBGAsYHQ/s0/Android-devrel-hiring-social%2B%25281%2529.png"
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