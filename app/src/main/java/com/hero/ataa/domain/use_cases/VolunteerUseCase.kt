package com.hero.ataa.domain.use_cases

import com.hero.ataa.R
import com.hero.ataa.data.local.repositories.UserRepository
import com.hero.ataa.data.remote.models.requests.VolunteerRequest
import com.hero.ataa.data.remote.repositories.FormsRepository
import com.hero.ataa.shared.AtaaException
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiText
import kotlinx.coroutines.flow.flow
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class VolunteerUseCase @Inject constructor(
    private val formsRepository: FormsRepository,
    private val userRepository: UserRepository,
) {
    operator fun invoke(
        name: String,
        nationalNumber: String,
        phoneNumber: String,
        birthDate: String,
        gender: String,
        governorate: String,
        place: String,
        address: String,
        about: String,
        job: String,
    ) = flow<DataState<Nothing>> {
        emit(DataState.Loading())
        val genderStr = when(gender) {
            "Male" -> "ذكر"
            "Female" -> "أنثى"
            else -> gender
        }
        try {
            val token = userRepository.user().token
            formsRepository.addVolunteer(
                token = token,
                volunteerRequest = VolunteerRequest(
                    name = name,
                    about = about,
                    work = job,
                    place = place,
                    governorate = governorate,
                    gender = genderStr,
                    birthDate = birthDate,
                    address = address,
                    phoneNumber = phoneNumber,
                    email = userRepository.user().email,
                    nationalNumber = nationalNumber,
                )
            )
            emit(DataState.SuccessWithoutData())
        } catch (ex: UnknownHostException) {
            emit(DataState.Error(UiText.ResourceText(R.string.can_not_reach_the_server)))
        } catch (ex: ConnectException) {
            emit(DataState.Error(UiText.ResourceText(R.string.bad_internet_connection)))
        } catch (ex: SocketTimeoutException) {
            emit(DataState.Error(UiText.ResourceText(R.string.bad_internet_connection)))
        } catch (ex: AtaaException) {
            emit(DataState.Error(UiText.ResourceText(resId = R.string.can_not_register_this_form_more_than_once)))
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