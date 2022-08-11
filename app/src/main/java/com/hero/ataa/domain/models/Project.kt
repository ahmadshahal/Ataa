package com.hero.ataa.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Project(

    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val title: String,

    @SerialName("description")
    val description: String,

    @SerialName("project_goal")
    val goals: String,

    @SerialName("categories")
    val tags: List<String>,

    @SerialName("target_money")
    val raisingGoal: Long,

    @SerialName("raised")
    val raised: Long,

    @SerialName("progress")
    val progress: Int = raised.times(100F).div(raisingGoal.toDouble()).toInt(),

    @SerialName("province")
    val location: String,

    // TODO: Remove Nullability
    @SerialName("image")
    val imageUrl: String?,

) : Parcelable
