package com.hero.ataa.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Project(
    val id: Int = 0,
    val title: String = "بناء وتأثيث مدرسة ابتدائية بمساحة 5500 م2 لتعليم ذوي الاحتياجات الخاصة",
    val description: String = "التعليم هو المحرك الأساسي لبناء المجتمعات ونشأة الحضارات، ومن أهم العوامل التي تساعد على التطور. وبالتعليم أيضاً احتلت دول الصدارة في التنمية وفي مختلف مناحي الحياة. والأهم من ذلك كله هو أن التعليم حق من حقوق الإنسان الأساسية وهو الهدف الرابع من الأهداف الإنمائية للألفية التي وضعتها الأمم المتحدة واتفقت عليها بلدان العالم لتلبية احتياجات الفئات الأكثر هشاشة في جميع أنحاء العالم.",
    val goals: String = "التعليم هو المحرك الأساسي لبناء المجتمعات ونشأة الحضارات، ومن أهم العوامل التي تساعد على التطور. وبالتعليم أيضاً احتلت دول الصدارة في التنمية وفي مختلف مناحي الحياة. والأهم من ذلك كله هو أن التعليم حق من حقوق الإنسان الأساسية وهو الهدف الرابع من الأهداف الإنمائية للألفية التي وضعتها الأمم المتحدة واتفقت عليها بلدان العالم لتلبية احتياجات الفئات الأكثر هشاشة في جميع أنحاء العالم.",
    val tags: List<String> = listOf("تعليم", "مشروع", "مشترك", "عام"),
    val progress: Double = 50.0,
    val raisingGoal: Long = 1000000000,
    val raised: Long = 750000000,
    val location: String = "دمشق",
    val imageUrl: String = "https://www.rtlnieuws.nl/sites/default/files/content/images/2019/08/22/android10.png?itok=85RlBYaw&width=2048&height=1152&impolicy=semi_dynamic"
) : Parcelable
