package ru.dast_6_tino.migration.viewmodels

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import ru.dast_6_tino.migration.data.PlantRepository

/**
 * Factory for creating a [PlantListViewModel] with a constructor that takes a [PlantRepository].
 */
class PlantListViewModelFactory(
    private val repository: PlantRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return PlantListViewModel(repository, handle) as T
    }
}