package ru.dast_6_tino.migration.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import ru.dast_6_tino.migration.utilities.getValue
import ru.dast_6_tino.migration.utilities.testPlant
import kotlinx.coroutines.Dispatchers
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.dast_6_tino.migration.data.AppDatabase
import ru.dast_6_tino.migration.data.GardenPlantingRepository
import ru.dast_6_tino.migration.data.PlantRepository

class PlantDetailViewModelTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var viewModel: PlantDetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()

        val plantRepo = PlantRepository.getInstance(appDatabase.plantDao())
        val gardenPlantingRepo = GardenPlantingRepository.getInstance(
            appDatabase.gardenPlantingDao(),
            Dispatchers.IO
        )
        viewModel = PlantDetailViewModel(plantRepo, gardenPlantingRepo, testPlant.plantId)
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    @Throws(InterruptedException::class)
    fun testDefaultValues() {
        assertFalse(getValue(viewModel.isPlanted))
    }

}
