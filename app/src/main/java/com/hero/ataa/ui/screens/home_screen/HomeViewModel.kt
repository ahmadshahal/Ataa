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
            text = "",
            url = "https://9to5google.com/wp-content/uploads/sites/4/2021/02/android-jetpack-header.png"
        ),
        Ad(
            text = "",
            url = "https://www.rtlnieuws.nl/sites/default/files/content/images/2019/08/22/android10.png?itok=85RlBYaw&width=2048&height=1152&impolicy=semi_dynamic"
        )
    )
}