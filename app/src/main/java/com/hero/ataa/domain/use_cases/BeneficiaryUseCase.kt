package com.hero.ataa.domain.use_cases

import com.hero.ataa.R
import com.hero.ataa.data.local.repositories.UserRepository
import com.hero.ataa.data.remote.models.requests.BeneficiaryRequest
import com.hero.ataa.data.remote.repositories.FormsRepository
import com.hero.ataa.shared.AtaaException
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiText
import kotlinx.coroutines.flow.flow
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class BeneficiaryUseCase @Inject constructor(
    private val formsRepository: FormsRepository,
    private val userRepository: UserRepository,
) {
    operator fun invoke(
        name: String,
        nationalNumber: String,
        birthDate: String,
        gender: String,
        socialStatus: String,
        kids: String,
        governorate: String,
        place: String,
        residenceStatus: String,
        phoneNumber: String,
        address: String,
        work: String,
        income: String,
        healthStatus: String,
        about: String,
    ) = flow<DataState<Nothing>> {
        emit(DataState.Loading())
        try {
            val token = userRepository.user().token
            formsRepository.addBeneficiary(
                token = token,
                beneficiaryRequest = BeneficiaryRequest(
                    name = name,
                    nationalNumber = nationalNumber,
                    email = userRepository.user().email,
                    phoneNumber = phoneNumber,
                    address = address,
                    birthDate = birthDate,
                    gender = gender,
                    governorate = governorate,
                    place = place,
                    residentialStatus = residenceStatus,
                    socialStatus = socialStatus,
                    work = work,
                    about = about,
                    healthStatus = healthStatus,
                    income = income,
                    kidsNumber = kids,
                )
            )
            emit(DataState.SuccessWithoutData())
        } catch (ex: UnknownHostException) {
            emit(DataState.Error(UiText.ResourceText(R.string.no_internet_connection)))
        } catch (ex: ConnectException) {
            emit(DataState.Error(UiText.ResourceText(R.string.no_internet_connection)))
        } catch (ex: SocketTimeoutException) {
            emit(DataState.Error(UiText.ResourceText(R.string.no_internet_connection)))
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