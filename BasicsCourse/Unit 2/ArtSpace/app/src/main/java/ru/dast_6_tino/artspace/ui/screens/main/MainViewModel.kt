package ru.dast_6_tino.artspace.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.dast_6_tino.artspace.data.Art
import ru.dast_6_tino.artspace.data.Repository

class MainViewModel : ViewModel() {

    val artStateFlow: StateFlow<Art>

    private val navigationSharedFlow = MutableSharedFlow<Navigation>()

    init {
        val artsFlow = flow { emit(Repository.getArts()) }

        val defaultArtFlow = artsFlow.take(1).map { arts -> arts.random() }

        val nextArtFlow = combine(navigationSharedFlow, artsFlow) { navigation, arts ->
            val selectedIndex = arts.indexOfFirst { it.image == navigation.id }
            if (selectedIndex == -1) return@combine arts.random()
            when (navigation) {
                is Navigation.Next -> if (selectedIndex + 1 == arts.size) arts.first() else arts[selectedIndex + 1]
                is Navigation.Previous -> if (selectedIndex == 0) arts.last() else arts[selectedIndex - 1]
            }
        }

        artStateFlow = merge(defaultArtFlow, nextArtFlow)
            .catch { emit(Art.default) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), Art.default)
    }

    fun previous(id: Int) = viewModelScope.launch { navigationSharedFlow.emit(Navigation.Previous(id)) }

    fun next(id: Int) = viewModelScope.launch { navigationSharedFlow.emit(Navigation.Next(id)) }

    private companion object {

        sealed interface Navigation {

            val id: Int

            data class Previous(override val id: Int) : Navigation

            data class Next(override val id: Int) : Navigation

        }

    }

}
