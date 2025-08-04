package ru.dast_6_tino.thirtydays.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

@Immutable
data class Day(
    @get:StringRes val title: Int,
    @get:StringRes val message: Int,
    @get:DrawableRes val image: Int,
)
