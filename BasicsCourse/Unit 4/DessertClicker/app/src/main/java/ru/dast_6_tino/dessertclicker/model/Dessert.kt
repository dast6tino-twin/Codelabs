package ru.dast_6_tino.dessertclicker.model

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable

/**
 * [Dessert] is the data class to represent the Dessert imageId, price, and startProductionAmount
 */
@Immutable
data class Dessert(
    @get:DrawableRes val imageId: Int,
    val price: Int,
    val startProductionAmount: Int,
)
