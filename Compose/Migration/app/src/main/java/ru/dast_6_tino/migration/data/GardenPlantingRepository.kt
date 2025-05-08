package ru.dast_6_tino.migration.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GardenPlantingRepository private constructor(
    private val gardenPlantingDao: GardenPlantingDao,
    private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun createGardenPlanting(plantId: String) {
        withContext(ioDispatcher) {
            val gardenPlanting = GardenPlanting(plantId)
            gardenPlantingDao.insertGardenPlanting(gardenPlanting)
        }
    }

    suspend fun removeGardenPlanting(gardenPlanting: GardenPlanting) {
        gardenPlantingDao.deleteGardenPlanting(gardenPlanting)
    }

    fun isPlanted(plantId: String) =
        gardenPlantingDao.isPlanted(plantId)

    fun getPlantedGardens() = gardenPlantingDao.getPlantedGardens()

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: GardenPlantingRepository? = null

        fun getInstance(gardenPlantingDao: GardenPlantingDao, ioDispatcher: CoroutineDispatcher) =
            instance ?: synchronized(this) {
                instance ?: GardenPlantingRepository(gardenPlantingDao, ioDispatcher).also {
                    instance = it
                }
            }
    }
}
