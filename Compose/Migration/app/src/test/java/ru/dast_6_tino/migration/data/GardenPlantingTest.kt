package ru.dast_6_tino.migration.data

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test
import java.util.Calendar
import java.util.Calendar.DAY_OF_MONTH
import java.util.Calendar.MONTH
import java.util.Calendar.YEAR

class GardenPlantingTest {

    @Test
    fun testDefaultValues() {
        val gardenPlanting = GardenPlanting("1")
        val cal = Calendar.getInstance()
        assertYMD(cal, gardenPlanting.plantDate)
        assertYMD(cal, gardenPlanting.lastWateringDate)
        assertEquals(0L, gardenPlanting.gardenPlantingId)
    }

    // Only Year/Month/Day precision is needed for comparing GardenPlanting Calendar entries
    private fun assertYMD(expectedCal: Calendar, actualCal: Calendar) {
        assertThat(actualCal.get(YEAR), equalTo(expectedCal.get(YEAR)))
        assertThat(actualCal.get(MONTH), equalTo(expectedCal.get(MONTH)))
        assertThat(actualCal.get(DAY_OF_MONTH), equalTo(expectedCal.get(DAY_OF_MONTH)))
    }

}
