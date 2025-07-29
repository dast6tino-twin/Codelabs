package ru.dast_6_tino.affirmations.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable

/**
 * [Affirmation] is the data class to represent the Affirmation text and imageResourceId
 */
@Immutable
data class Affirmation(
    @get:StringRes val stringResourceId: Int,
    @get:DrawableRes val imageResourceId: Int,
)
