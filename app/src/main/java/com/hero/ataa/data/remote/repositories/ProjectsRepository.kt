package com.hero.ataa.data.remote.repositories

import com.hero.ataa.data.remote.models.requests.PayRequest
import com.hero.ataa.data.remote.models.responses.PayResponse
import com.hero.ataa.data.remote.models.responses.ProjectValueResponse
import com.hero.ataa.data.remote.models.responses.ProjectsResponse
import com.hero.ataa.data.remote.models.responses.ReceiptsResponse
import com.hero.ataa.di.BASE_URL
import com.hero.ataa.domain.models.Ad
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

private const val GET_ALL_PROJECTS_URL = "/m/projects"
private const val GET_PROJECTS_BY_TAG_URL = "/m/projects/"
private const val DONATE_URL = "/m/donate"
private const val RECEIPTS_URL = "/m/profile/donations"
private const val MISKEEN_URL = "/m/data/miskeen"
private const val SACRIFICE_URL = "/m/data/sacrifice"

class ProjectsRepository @Inject constructor(
    private val httpClient: HttpClient
) {
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

    suspend fun getAllProjects(): ProjectsResponse {
        return httpClient.get {
            url("${BASE_URL}${GET_ALL_PROJECTS_URL}")
        }.body()
    }

    suspend fun getProjects(categoryApiKey: String): ProjectsResponse {
        return httpClient.get {
            url("${BASE_URL}${GET_PROJECTS_BY_TAG_URL}${categoryApiKey}")
        }.body()
    }

    suspend fun getMiskeenValue(): ProjectValueResponse {
        return httpClient.get {
            url("${BASE_URL}${MISKEEN_URL}")
        }.body()
    }

    suspend fun getSacrificeValue(): ProjectValueResponse {
        return httpClient.get {
            url("${BASE_URL}${SACRIFICE_URL}")
        }.body()
    }

    suspend fun getReceipts(token: String): ReceiptsResponse {
        return httpClient.get {
            url("${BASE_URL}${RECEIPTS_URL}")
            header(HttpHeaders.Authorization, "Bearer $token")
        }.body()
    }

    suspend fun pay(token: String, payRequest: PayRequest): PayResponse {
        return httpClient.post {
            url("${BASE_URL}${DONATE_URL}")
            header(HttpHeaders.Authorization, "Bearer $token")
            setBody(payRequest)
        }.body()
    }
}