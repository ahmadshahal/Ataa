package com.hero.ataa.ui.screens.project_screen

import androidx.lifecycle.ViewModel
import com.hero.ataa.data.local.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
    userRepository: UserRepository
) : ViewModel() {
    val userLoggedInFlow = userRepository.loggedInFlow
}