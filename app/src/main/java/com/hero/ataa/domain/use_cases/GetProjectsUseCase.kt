package com.hero.ataa.domain.use_cases

import com.hero.ataa.R
import com.hero.ataa.domain.models.Project
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProjectsUseCase @Inject constructor() {
    operator fun invoke(categoryApiKey: String) = flow<DataState<List<Project>>> {
        emit(DataState.Loading())
        try {
            delay(4000)
            val list = listOf(
                Project(
                    imageUrl = "https://1.bp.blogspot.com/-4G4zVhAxueg/YKLth6HiL_I/AAAAAAAAQhM/JiTsOudkdXgb94qpNYI66jEGlauS0CETQCLcBGAsYHQ/s0/android-whats-new-in-jetpack-v2.png",
                    progress = 30.0,
                ), Project(
                    imageUrl = "https://1.bp.blogspot.com/-b1_n6tOHvWU/YKMssWEjo-I/AAAAAAAAQjk/vIJQsAPUpRQKxR44GoCbm3CtRgr8tVBKACLcBGAsYHQ/s0/Android_NewForDevelopers_1024x512_updated.png",
                    progress = 90.0,
                ), Project(
                    imageUrl = "https://1.bp.blogspot.com/-4p_wvqXkDhg/YKMSkzlZCZI/AAAAAAAAQis/vWgHdB-a3U4syecmyO0O3zuBr3u3t5wVQCLcBGAsYHQ/s0/Android_SecurityAndPrivacy_1024x512-01_Updated%2B%25281%2529.png",
                ), Project(
                    imageUrl = "https://1.bp.blogspot.com/-Zde9ioLE3SY/YWh7qiquXKI/AAAAAAAARCU/m6D-qJJe6QowYPcDWUtb3-YzFGn9xIaUwCLcBGAsYHQ/s0/Android-get-ready-to-sumbit-your-data-safety-secton-social.png",
                )
            )
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