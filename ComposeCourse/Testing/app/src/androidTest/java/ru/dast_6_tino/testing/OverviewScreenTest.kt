package ru.dast_6_tino.testing

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.dast_6_tino.testing.ui.overview.OverviewBody
import ru.dast_6_tino.testing.ui.theme.RallyTheme

class OverviewScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            RallyTheme {
                OverviewBody()
            }
        }
    }

    @Test
    fun overviewScreen_alertsDisplayed() {
        composeTestRule.onNodeWithText("Alerts")
            .assertIsDisplayed()
    }

}
