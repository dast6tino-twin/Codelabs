package ru.dast_6_tino.artspace

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*
import ru.dast_6_tino.artspace.data.Art
import ru.dast_6_tino.artspace.data.Repository

class MainViewModel : ViewModel() {

    val artFlow: Flow<Art>

    private val navigationSharedFlow = MutableSharedFlow<Navigation>()

    init {
        val artsFlow = flow { emit(Repository.getArts()) }

        val defaultArtFlow = artsFlow.map { arts -> arts.random() }

        val nextArtFlow = combine(navigationSharedFlow, artsFlow) { navigation, arts ->
            val selectedIndex = arts.indexOfFirst { it.image == navigation.id }
            if (selectedIndex == -1) return@combine arts.random()
            when (navigation) {
                is Navigation.Next -> if (selectedIndex + 1 == arts.size) arts.first() else arts[selectedIndex + 1]
                is Navigation.Previous -> if (selectedIndex == 0) arts.last() else arts[selectedIndex - 1]
            }
        }

        artFlow = merge(defaultArtFlow, nextArtFlow)
    }

    suspend fun previous(id: Int) = navigationSharedFlow.emit(Navigation.Previous(id))

    suspend fun next(id: Int) = navigationSharedFlow.emit(Navigation.Next(id))

    private companion object {

        sealed interface Navigation {

            val id: Int

            data class Previous(override val id: Int) : Navigation

            data class Next(override val id: Int) : Navigation

        }

    }

}
