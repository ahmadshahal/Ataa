package com.hero.ataa.data.remote.repositories

import com.hero.ataa.data.remote.models.requests.BeneficiaryRequest
import com.hero.ataa.di.BASE_API_URL
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

private const val BENEFICIARY_URL = "/w/beneficiary/create"
private const val VOLUNTEER_URL = "w/employee/create"

class FormsRepository @Inject constructor(
    private val httpClient: HttpClient
) {
    suspend fun addBeneficiary(token: String, beneficiaryRequest: BeneficiaryRequest) {
        return httpClient.post {
            url("${BASE_API_URL}${BENEFICIARY_URL}")
            header(HttpHeaders.Authorization, "Bearer $token")
            setBody(beneficiaryRequest)
        }.body()
    }
}