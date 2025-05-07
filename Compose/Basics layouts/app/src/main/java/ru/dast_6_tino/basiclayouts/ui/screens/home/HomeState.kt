package ru.dast_6_tino.basiclayouts.ui.screens.home

import ru.dast_6_tino.basiclayouts.AlignYourBody
import ru.dast_6_tino.basiclayouts.FavoriteCollection

data class HomeState(
    val alignYourBodyData: List<AlignYourBody>,
    val favoriteCollectionData: List<FavoriteCollection>,
)
