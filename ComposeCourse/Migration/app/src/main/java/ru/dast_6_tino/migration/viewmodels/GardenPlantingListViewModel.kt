package ru.dast_6_tino.migration.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.dast_6_tino.migration.data.GardenPlantingRepository
import ru.dast_6_tino.migration.data.PlantAndGardenPlantings

class GardenPlantingListViewModel internal constructor(
    gardenPlantingRepository: GardenPlantingRepository
) : ViewModel() {
    val plantAndGardenPlantings: LiveData<List<PlantAndGardenPlantings>> =
        gardenPlantingRepository.getPlantedGardens()
}
