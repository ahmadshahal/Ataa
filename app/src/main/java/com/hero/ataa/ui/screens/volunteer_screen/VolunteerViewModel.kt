package com.hero.ataa.ui.screens.volunteer_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hero.ataa.R
import com.hero.ataa.domain.use_cases.VolunteerUseCase
import com.hero.ataa.shared.DataState
import com.hero.ataa.shared.UiEvent
import com.hero.ataa.shared.UiText
import com.hero.ataa.ui.screens.beneficiary_screen.FormUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VolunteerViewModel @Inject constructor(
    private val volunteerUseCase: VolunteerUseCase,
) : ViewModel() {
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

    val dateOfBirthFieldText = mutableStateOf("")
    val isErrorDateOfBirthField = mutableStateOf(false)
    val dateOfBirthFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))

    val governorateFieldText = mutableStateOf("")
    val isErrorGovernorateField = mutableStateOf(false)
    val governorateFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))

    val placeFieldText = mutableStateOf("")
    val isErrorPlaceField = mutableStateOf(false)
    val placeFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))

    val phoneNumberFieldText = mutableStateOf("")
    val isErrorPhoneNumberField = mutableStateOf(false)
    val phoneNumberFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))

    val addressFieldText = mutableStateOf("")
    val isErrorAddressField = mutableStateOf(false)
    val addressFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))

    val workFieldText = mutableStateOf("")
    val isErrorWorkField = mutableStateOf(false)
    val workFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))

    val aboutFieldText = mutableStateOf("")
    val isErrorAboutField = mutableStateOf(false)
    val aboutFieldErrorMsg = mutableStateOf<UiText>(UiText.DynamicText(""))

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
        if(genderFieldText.value.isEmpty()) {
            isErrorGenderField.value = true
            genderFieldErrorMsg.value = UiText.ResourceText(R.string.cant_be_empty)
            valid = false
        }
        else {
            isErrorGenderField.value = false
            genderFieldErrorMsg.value = UiText.DynamicText("")
        }
        return valid
    }

    fun secondFormValidation(): Boolean {
        var valid = true
        if(governorateFieldText.value.isEmpty()) {
            isErrorGovernorateField.value = true
            governorateFieldErrorMsg.value = UiText.ResourceText(R.string.cant_be_empty)
            valid = false
        }
        else {
            isErrorGovernorateField.value = false
            governorateFieldErrorMsg.value = UiText.DynamicText("")
        }
        if(placeFieldText.value.isEmpty()) {
            isErrorPlaceField.value = true
            placeFieldErrorMsg.value = UiText.ResourceText(R.string.cant_be_empty)
            valid = false
        }
        else {
            isErrorPlaceField.value = false
            placeFieldErrorMsg.value = UiText.DynamicText("")
        }
        if(phoneNumberFieldText.value.isEmpty()) {
            isErrorPhoneNumberField.value = true
            phoneNumberFieldErrorMsg.value = UiText.ResourceText(R.string.cant_be_empty)
            valid = false
        }
        else {
            isErrorPhoneNumberField.value = false
            phoneNumberFieldErrorMsg.value = UiText.DynamicText("")
        }
        if(addressFieldText.value.isEmpty()) {
            isErrorAddressField.value = true
            addressFieldErrorMsg.value = UiText.ResourceText(R.string.cant_be_empty)
            valid = false
        }
        else {
            isErrorAddressField.value = false
            addressFieldErrorMsg.value = UiText.DynamicText("")
        }
        return valid
    }

    fun thirdFormValidation(): Boolean {
        var valid = true
        if(workFieldText.value.isEmpty()) {
            isErrorWorkField.value = true
            workFieldErrorMsg.value = UiText.ResourceText(R.string.cant_be_empty)
            valid = false
        }
        else {
            isErrorWorkField.value = false
            workFieldErrorMsg.value = UiText.DynamicText("")
        }
        if(aboutFieldText.value.isEmpty()) {
            isErrorAboutField.value = true
            aboutFieldErrorMsg.value = UiText.ResourceText(R.string.cant_be_empty)
            valid = false
        }
        else {
            isErrorAboutField.value = false
            aboutFieldErrorMsg.value = UiText.DynamicText("")
        }
        return valid
    }

    fun register() {
        viewModelScope.launch {
            volunteerUseCase(
                name = fullNameFieldText.value,
                nationalNumber = nationalNumberFieldText.value,
                birthDate = dateOfBirthFieldText.value,
                gender = genderFieldText.value,
                governorate = governorateFieldText.value,
                place = placeFieldText.value,
                phoneNumber = phoneNumberFieldText.value,
                address = addressFieldText.value,
                job = workFieldText.value,
                about = aboutFieldText.value,
            ).collect { dataState ->
                when (dataState) {
                    is DataState.Loading -> {
                        _uiState.value = FormUiState.Loading
                    }
                    is DataState.Error -> {
                        _uiState.value = FormUiState.Initial
                        _uiEvent.send(
                            UiEvent.ShowSnackBar(
                                message = dataState.message,
                            )
                        )
                    }
                    is DataState.SuccessWithoutData -> {
                        _uiState.value = FormUiState.Initial
                        _uiEvent.send(UiEvent.PopBackStack)
                    }
                    else -> Unit
                }
            }
        }
    }
}