package com.hero.ataa.ui.screens.beneficiary_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.hero.ataa.R
import com.hero.ataa.shared.UiEvent
import com.hero.ataa.shared.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class BeneficiaryViewModel @Inject constructor() : ViewModel() {

    private val _uiEvent: Channel<UiEvent> = Channel()
    val uiEvent: Flow<UiEvent>
        get() = _uiEvent.receiveAsFlow()

    private val _uiState = mutableStateOf<FormUiState>(FormUiState.Initial)
    val uiState: State<FormUiState>
        get() = _uiState

    val fullNameFieldText = mutableStateOf("")
    val isErrorNameField = mutableStateOf(false)
    val nameFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))

    val nationalNumberFieldText = mutableStateOf("")
    val isErrorNationalNumberField = mutableStateOf(false)
    val nationalNumberFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))

    val genderFieldText = mutableStateOf("")
    val isErrorGenderField = mutableStateOf(false)
    val genderFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))

    val socialStatusFieldText = mutableStateOf("")
    val isErrorSocialStatusField = mutableStateOf(false)
    val socialStatusFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))

    val kidsFieldText = mutableStateOf("")
    val isErrorKidsField = mutableStateOf(false)
    val kidsFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))

    val dateOfBirthFieldText = mutableStateOf("")
    val isErrorDateOfBirthField = mutableStateOf(false)
    val dateOfBirthFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))

    val governorateFieldText = mutableStateOf("")
    val isErrorGovernorateField = mutableStateOf(false)
    val governorateFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))

    val placeFieldText = mutableStateOf("")
    val isErrorPlaceField = mutableStateOf(false)
    val placeFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))

    val residenceFieldText = mutableStateOf("")
    val isErrorResidenceField = mutableStateOf(false)
    val residenceFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))

    val phoneNumberFieldText = mutableStateOf("")
    val isErrorPhoneNumberField = mutableStateOf(false)
    val phoneNumberFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))

    val addressFieldText = mutableStateOf("")
    val isErrorAddressField = mutableStateOf(false)
    val addressFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))

    fun firstFormValidation(): Boolean {
        var valid = true
        if(fullNameFieldText.value.isEmpty()) {
            isErrorNameField.value = true
            nameFieldErrorMsg.value = UiText.ResourceText(R.string.cant_be_empty)
            valid = false
        }
        else {
            isErrorNameField.value = false
            nameFieldErrorMsg.value = UiText.DynamicText("")
        }
        if(nationalNumberFieldText.value.isEmpty()) {
            isErrorNationalNumberField.value = true
            nationalNumberFieldErrorMsg.value = UiText.ResourceText(R.string.cant_be_empty)
            valid = false
        }
        else {
            isErrorNationalNumberField.value = false
            nationalNumberFieldErrorMsg.value = UiText.DynamicText("")
        }
        if(dateOfBirthFieldText.value.isEmpty()) {
            isErrorDateOfBirthField.value = true
            dateOfBirthFieldErrorMsg.value = UiText.ResourceText(R.string.cant_be_empty)
            valid = false
        }
        else {
            isErrorDateOfBirthField.value = false
            dateOfBirthFieldErrorMsg.value = UiText.DynamicText("")
        }
        if(socialStatusFieldText.value.isEmpty()) {
            isErrorSocialStatusField.value = true
            socialStatusFieldErrorMsg.value = UiText.ResourceText(R.string.cant_be_empty)
            valid = false
        }
        else {
            isErrorSocialStatusField.value = false
            socialStatusFieldErrorMsg.value = UiText.DynamicText("")
        }
        if(genderFieldText.value.isEmpty()) {
            isErrorGenderField.value = true
            genderFieldErrorMsg.value = UiText.ResourceText(R.string.cant_be_empty)
            valid = false
        }
        else {
            isErrorGenderField.value = false
            genderFieldErrorMsg.value = UiText.DynamicText("")
        }
        if(kidsFieldText.value.isEmpty()) {
            isErrorKidsField.value = true
            kidsFieldErrorMsg.value = UiText.ResourceText(R.string.cant_be_empty)
            valid = false
        }
        else {
            isErrorKidsField.value = false
            kidsFieldErrorMsg.value = UiText.DynamicText("")
        }
        return valid
    }
}