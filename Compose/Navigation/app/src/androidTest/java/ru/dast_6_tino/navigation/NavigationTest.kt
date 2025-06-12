package ru.dast_6_tino.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun setupNavHost() {
        composeTestRule.setContent {
            // Creates a TestNavHostController
            navController = TestNavHostController(LocalContext.current).apply {
                // Sets a ComposeNavigator to the navController so it can navigate through composables
                navigatorProvider.addNavigator(navigator = ComposeNavigator())
            }
            RallyNavHost(navController)
        }
    }

    @Test
    fun verifyOverviewStartDestination() {
        composeTestRule.onNodeWithContentDescription("Overview Screen")
            .assertIsDisplayed()
    }

    @Test
    fun clickAllAccounts_navigateToAccounts() {
        composeTestRule.onNodeWithContentDescription("All Accounts")
            .performClick()

        composeTestRule.onNodeWithContentDescription("Accounts Screen")
            .assertIsDisplayed()
    }

    @Test
    fun clickAllBills_navigateToBills() {
        composeTestRule.onNodeWithContentDescription("All Bills")
            .performScrollTo()
            .performClick()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, "bills")
    }

}
