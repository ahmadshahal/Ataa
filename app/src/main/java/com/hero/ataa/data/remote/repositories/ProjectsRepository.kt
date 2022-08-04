package com.hero.ataa.data.remote.repositories

import com.hero.ataa.domain.models.Ad
import com.hero.ataa.domain.models.Project
import com.hero.ataa.domain.models.Receipt
import javax.inject.Inject

class ProjectsRepository @Inject constructor() {
    fun getAds(): List<Ad> {
        return listOf<Ad>(
            Ad(
                text = "",
                url = "https://1.bp.blogspot.com/-Zde9ioLE3SY/YWh7qiquXKI/AAAAAAAARCU/m6D-qJJe6QowYPcDWUtb3-YzFGn9xIaUwCLcBGAsYHQ/s0/Android-get-ready-to-sumbit-your-data-safety-secton-social.png",
            ),
            Ad(
                text = "",
                url = "https://1.bp.blogspot.com/-4G4zVhAxueg/YKLth6HiL_I/AAAAAAAAQhM/JiTsOudkdXgb94qpNYI66jEGlauS0CETQCLcBGAsYHQ/s0/android-whats-new-in-jetpack-v2.png",
            ),
            Ad(
                text = "",
                url = "https://1.bp.blogspot.com/-b1_n6tOHvWU/YKMssWEjo-I/AAAAAAAAQjk/vIJQsAPUpRQKxR44GoCbm3CtRgr8tVBKACLcBGAsYHQ/s0/Android_NewForDevelopers_1024x512_updated.png",
            ),
        )
    }

    fun getAllProjects(): List<Project> {
        return listOf(
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
    }

    fun getMiskeenValue(): Int {
        return 10000
    }

    fun getSacrificeValue(): Int {
        return 900000
    }

    fun getProjects(categoryApiKey: String): List<Project> {
        return listOf(
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
    }

    fun getReceipts(token: String): List<Receipt> {
        return listOf(
            Receipt(title = "", tags = listOf("إطعام مسكين")),
            Receipt(),
            Receipt(title = "", tags = listOf("صدقة")),
            Receipt(),
        )
    }

    fun pay(token: String, projectId: String, donationValue: String): String {
        return "https://www.facebook.com/ahmad.shahal.2001/"
    }
}