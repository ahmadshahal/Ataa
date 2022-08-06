package com.hero.ataa.data.remote.repositories

import com.hero.ataa.data.remote.models.requests.PayRequest
import com.hero.ataa.data.remote.models.responses.*
import com.hero.ataa.di.BASE_API_URL
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
private const val ADS_URL = "/m/projects/ads"

class ProjectsRepository @Inject constructor(
    private val httpClient: HttpClient
) {
    suspend fun getAds(): AdsResponse {
        return httpClient.get {
            url("${BASE_API_URL}${ADS_URL}")
        }.body()
    }

    suspend fun getAllProjects(): ProjectsResponse {
        return httpClient.get {
            url("${BASE_API_URL}${GET_ALL_PROJECTS_URL}")
        }.body()
    }

    suspend fun getProjects(categoryApiKey: String): ProjectsResponse {
        return httpClient.get {
            url("${BASE_API_URL}${GET_PROJECTS_BY_TAG_URL}${categoryApiKey}")
        }.body()
    }

    suspend fun getMiskeenValue(): ProjectValueResponse {
        return httpClient.get {
            url("${BASE_API_URL}${MISKEEN_URL}")
        }.body()
    }

    suspend fun getSacrificeValue(): ProjectValueResponse {
        return httpClient.get {
            url("${BASE_API_URL}${SACRIFICE_URL}")
        }.body()
    }

    suspend fun getReceipts(token: String): ReceiptsResponse {
        return httpClient.get {
            url("${BASE_API_URL}${RECEIPTS_URL}")
            header(HttpHeaders.Authorization, "Bearer $token")
        }.body()
    }

    suspend fun pay(token: String, payRequest: PayRequest): PayResponse {
        return httpClient.post {
            url("${BASE_API_URL}${DONATE_URL}")
            header(HttpHeaders.Authorization, "Bearer $token")
            setBody(payRequest)
        }.body()
    }
}