package ru.dast_6_tino.migration.utilities

import android.content.Context
import androidx.fragment.app.Fragment
import ru.dast_6_tino.migration.data.AppDatabase
import ru.dast_6_tino.migration.data.GardenPlantingRepository
import ru.dast_6_tino.migration.data.PlantRepository
import ru.dast_6_tino.migration.viewmodels.GardenPlantingListViewModelFactory
import ru.dast_6_tino.migration.viewmodels.PlantDetailViewModelFactory
import ru.dast_6_tino.migration.viewmodels.PlantListViewModelFactory
import kotlinx.coroutines.Dispatchers

/**
 * Static methods used to inject classes needed for various Activities and Fragments.
 */
object InjectorUtils {

    private val ioDispatcher = Dispatchers.IO

    private fun getPlantRepository(context: Context): PlantRepository {
        return PlantRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).plantDao()
        )
    }

    private fun getGardenPlantingRepository(context: Context): GardenPlantingRepository {
        return GardenPlantingRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).gardenPlantingDao(),
            ioDispatcher
        )
    }

    fun provideGardenPlantingListViewModelFactory(
        context: Context
    ): GardenPlantingListViewModelFactory {
        return GardenPlantingListViewModelFactory(getGardenPlantingRepository(context))
    }

    fun providePlantListViewModelFactory(fragment: Fragment): PlantListViewModelFactory {
        return PlantListViewModelFactory(getPlantRepository(fragment.requireContext()), fragment)
    }

    fun providePlantDetailViewModelFactory(
        context: Context,
        plantId: String
    ): PlantDetailViewModelFactory {
        return PlantDetailViewModelFactory(
            getPlantRepository(context),
            getGardenPlantingRepository(context),
            plantId
        )
    }
}
