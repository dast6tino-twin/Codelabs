package ru.dast_6_tino.tiptime

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.dast_6_tino.tiptime.ui.screens.calculateTip
import java.text.NumberFormat

class TipCalculatorTests {

    @Test
    fun calculateTip_20PercentNoRoundup() {
        val amount = 10.0
        val tipPercent = 20.0
        val currencyFormat = NumberFormat.getCurrencyInstance()
        val expectedTips = currencyFormat.format(2)
        val actualTips = calculateTip(amount, tipPercent, false)
        assertEquals(expectedTips, actualTips)
    }

}
