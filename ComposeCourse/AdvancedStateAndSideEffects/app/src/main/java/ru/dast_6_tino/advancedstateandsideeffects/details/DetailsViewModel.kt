package ru.dast_6_tino.advancedstateandsideeffects.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.dast_6_tino.advancedstateandsideeffects.base.Result
import ru.dast_6_tino.advancedstateandsideeffects.data.DestinationsRepository
import ru.dast_6_tino.advancedstateandsideeffects.data.ExploreModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val destinationsRepository: DestinationsRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val cityName = savedStateHandle.get<String>(KEY_ARG_DETAILS_CITY_NAME)!!

    val cityDetails: Result<ExploreModel>
        get() = destinationsRepository.getDestination(cityName)
            ?.let { destination -> Result.Success(destination) }
            ?: Result.Error(IllegalArgumentException("City doesn't exist"))

}
