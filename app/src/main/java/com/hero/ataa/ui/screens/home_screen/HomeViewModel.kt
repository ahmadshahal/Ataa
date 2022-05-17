package com.hero.ataa.ui.screens.home_screen

import androidx.lifecycle.ViewModel
import com.hero.ataa.domain.models.Ad
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    val adsList = mutableListOf<Ad>(
        Ad(
            text = "مبادرة لا للجوع بالتعاون مع جمعية الأيادي البيضاء",
            url = "https://i.ibb.co/1d7Th9Z/charity.png"
        ),
        Ad(
            text = "مبادرة لا للجوع بالتعاون مع جمعية الأيادي البيضاء",
            url = "https://i.ibb.co/1d7Th9Z/charity.png"
        ),
        Ad(
            text = "مبادرة لا للجوع بالتعاون مع جمعية الأيادي البيضاء",
            url = "https://i.ibb.co/1d7Th9Z/charity.png"
        )
    )
}