package ru.dast_6_tino.advancedstateandsideeffects.details

import ru.dast_6_tino.advancedstateandsideeffects.data.ExploreModel

data class DetailsUiState(
    val cityDetails: ExploreModel? = null,
    val isLoading: Boolean = false,
    val throwError: Boolean = false,
)
