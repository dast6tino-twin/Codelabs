package ru.dast_6_tino.tiptime

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.dast_6_tino.tiptime.ui.screens.TipTimeScreen
import ru.dast_6_tino.tiptime.ui.theme.TipTimeTheme
import java.text.NumberFormat

class TipUITests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            TipTimeTheme {
                TipTimeScreen()
            }
        }
    }

    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.onNodeWithText("Bill Amount").performTextInput("10")
        val percentageText = "Tip Percentage"
        composeTestRule.onNode(
            matcher = hasContentDescription("Clear") and hasParent(matcher = hasText(percentageText)),
        ).performClick()
        composeTestRule.onNodeWithText(percentageText).performTextInput("20")
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        composeTestRule.onNodeWithText("Tip Amount: $expectedTip")
            .assertExists("No node with this text was found.")
    }

}
