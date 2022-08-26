package com.hero.ataa.data.remote.models.responses

import com.hero.ataa.domain.models.Project
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProjectsResponse(
    @SerialName("projects")
    val projects: List<Project>
)