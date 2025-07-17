package ru.dast_6_tino.advancedstateandsideeffects.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.dast_6_tino.advancedstateandsideeffects.data.DestinationsRepository
import ru.dast_6_tino.advancedstateandsideeffects.data.ExploreModel
import ru.dast_6_tino.advancedstateandsideeffects.di.DefaultDispatcher
import javax.inject.Inject
import kotlin.random.Random

const val MAX_PEOPLE = 4

@HiltViewModel
class MainViewModel @Inject constructor(
    private val destinationsRepository: DestinationsRepository,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher,
) : ViewModel() {

    val hotels: List<ExploreModel> = destinationsRepository.hotels
    val restaurants: List<ExploreModel> = destinationsRepository.restaurants

    val suggestedDestinationsFlow: Flow<List<ExploreModel>>

    private val peopleSharedFlow = MutableSharedFlow<Int>()
    private val destinationSharedFlow = MutableSharedFlow<String>()

    init {
        val defaultDestinationsFlow = flow {
            emit(destinationsRepository.destinations)
        }
            .onStart { emit(emptyList()) }

        val destinationsFromPeopleFlow = combine(peopleSharedFlow, defaultDestinationsFlow) { people, destinations ->
            if (people > MAX_PEOPLE) {
                emptyList()
            } else {
                destinations.shuffled(Random(people * (1..100).shuffled().first()))
            }
        }

        val destinationsFlow = combine(destinationSharedFlow, defaultDestinationsFlow) { destination, destinations ->
            destinations.filter { it.city.nameToDisplay.contains(destination) }
        }

        suggestedDestinationsFlow = merge(defaultDestinationsFlow, destinationsFromPeopleFlow, destinationsFlow)
            .flowOn(defaultDispatcher)
    }

    fun updatePeople(people: Int) {
        viewModelScope.launch {
            peopleSharedFlow.emit(people)
        }
    }

    fun toDestinationChanged(destination: String) {
        viewModelScope.launch {
            destinationSharedFlow.emit(destination)
        }
    }

}
