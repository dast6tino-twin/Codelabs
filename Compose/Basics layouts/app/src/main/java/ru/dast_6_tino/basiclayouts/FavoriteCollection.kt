package ru.dast_6_tino.basiclayouts

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class FavoriteCollection(
    val id: Int,
    @DrawableRes val drawableRes: Int,
    @StringRes val textRes: Int,
)
