package ru.dast_6_tino.courses.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @get:DrawableRes val image: Int,
    @get:StringRes val title: Int,
    val goal: Int,
)
