package ru.dast_6_tino.testing

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TopAppBarTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setupRallyApp() = composeTestRule.setContent {
        RallyApp()
    }.apply {
        composeTestRule.onRoot(useUnmergedTree = true).printToLog("currentLabelExists")  // Prints semantics tree
    }

    @Test
    fun rallyTopAppBarTest_currentLabelExists() {
        val overviewName = RallyScreen.Overview.name
        composeTestRule.onNode(
            matcher = hasText(overviewName.uppercase()) and
                hasParent(
                    matcher = hasContentDescription(overviewName),
                ),
            useUnmergedTree = true,
        )
            .assertExists()
    }

    @Test
    fun rallyTopAppBarTest_clickedLabelExists() {
        val accountScreen = RallyScreen.Accounts
        val accountName = accountScreen.name
        composeTestRule.onNodeWithContentDescription(accountName)
            .performClick()

        composeTestRule.onNode(
            matcher = hasText(accountName.uppercase()) and
                hasParent(
                    matcher = hasContentDescription(accountName),
                ),
            useUnmergedTree = true,
        )
            .assertExists()
    }

}
