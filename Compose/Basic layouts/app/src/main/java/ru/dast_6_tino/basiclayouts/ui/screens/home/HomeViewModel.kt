package ru.dast_6_tino.basiclayouts.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ru.dast_6_tino.basiclayouts.AlignYourBody
import ru.dast_6_tino.basiclayouts.FavoriteCollection
import ru.dast_6_tino.basiclayouts.R

class HomeViewModel : ViewModel() {

    private var _state by mutableStateOf(initialState)
    val state: HomeState get() = _state

    private val initialState: HomeState
        get() = HomeState(
            alignYourBodyData = getAlignYourBodyData(),
            favoriteCollectionData = getFavoriteCollectionData(),
        )

    private fun getAlignYourBodyData(): List<AlignYourBody> = buildList {
        this += AlignYourBody(
            id = 0,
            drawableRes = R.drawable.ab1_inversions,
            textRes = R.string.ab1_inversions,
        )
        this += AlignYourBody(
            id = 1,
            drawableRes = R.drawable.ab2_quick_yoga,
            textRes = R.string.ab2_quick_yoga,
        )
        this += AlignYourBody(
            id = 2,
            drawableRes = R.drawable.ab3_stretching,
            textRes = R.string.ab3_stretching,
        )
        this += AlignYourBody(
            id = 3,
            drawableRes = R.drawable.ab4_tabata,
            textRes = R.string.ab4_tabata,
        )
        this += AlignYourBody(
            id = 4,
            drawableRes = R.drawable.ab5_hiit,
            textRes = R.string.ab5_hiit,
        )
        this += AlignYourBody(
            id = 5,
            drawableRes = R.drawable.ab6_pre_natal_yoga,
            textRes = R.string.ab6_pre_natal_yoga,
        )
    }

    private fun getFavoriteCollectionData(): List<FavoriteCollection> = buildList {
        this += FavoriteCollection(
            id = 0,
            drawableRes = R.drawable.fc1_short_mantras,
            textRes = R.string.fc1_short_mantras,
        )
        this += FavoriteCollection(
            id = 1,
            drawableRes = R.drawable.fc2_nature_meditations,
            textRes = R.string.fc2_nature_meditations,
        )
        this += FavoriteCollection(
            id = 2,
            drawableRes = R.drawable.fc3_stress_and_anxiety,
            textRes = R.string.fc3_stress_and_anxiety,
        )
        this += FavoriteCollection(
            id = 3,
            drawableRes = R.drawable.fc4_self_massage,
            textRes = R.string.fc4_self_massage,
        )
        this += FavoriteCollection(
            id = 4,
            drawableRes = R.drawable.fc5_overwhelmed,
            textRes = R.string.fc5_overwhelmed,
        )
        this += FavoriteCollection(
            id = 5,
            drawableRes = R.drawable.fc6_nightly_wind_down,
            textRes = R.string.fc6_nightly_wind_down,
        )
    }

}
