package com.hero.ataa.domain.use_cases

import com.hero.ataa.R
import com.hero.ataa.domain.models.Receipt
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetReceiptsUseCase @Inject constructor() {
    operator fun invoke() = flow<DataState<List<Receipt>>> {
        emit(DataState.Loading())
        try {
            delay(1000)
            val list = listOf(
                Receipt(title = "", tags = listOf("إطعام مسكين")),
                Receipt(),
                Receipt(title = "", tags = listOf("صدقة")),
                Receipt(),
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