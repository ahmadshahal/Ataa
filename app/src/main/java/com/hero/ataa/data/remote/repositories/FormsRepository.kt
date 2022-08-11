package com.hero.ataa.data.remote.repositories

import com.hero.ataa.data.remote.models.requests.BeneficiaryRequest
import com.hero.ataa.data.remote.models.requests.VolunteerRequest
import com.hero.ataa.di.BASE_API_URL
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject

private const val BENEFICIARY_URL = "/m/application/benef"
private const val VOLUNTEER_URL = "m/application/volunteer"

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

    suspend fun addVolunteer(token: String, volunteerRequest: VolunteerRequest) {
        return httpClient.post {
            url("${BASE_API_URL}${VOLUNTEER_URL}")
            header(HttpHeaders.Authorization, "Bearer $token")
            setBody(volunteerRequest)
        }.body()
    }
}