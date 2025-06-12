package ru.dast_6_tino.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.dast_6_tino.navigation.ui.components.RallyTabRow
import ru.dast_6_tino.navigation.ui.theme.RallyTheme

/**
 * This Activity recreates part of the Rally Material Study from
 * https://material.io/design/material-studies/rally.html
 */
class RallyActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ToDo Edge to edge fix, but it do not work
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        //

        setContent {
            RallyApp()
        }
    }

}

@Composable
fun RallyApp() = RallyTheme {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()

    Scaffold(
        topBar = {
            RallyTabRow(
                allScreens = Destinations.tabs,
                // Pass the callback like this,
                // defining the navigation action when a tab is selected:
                onTabSelected = { screen ->
                    navController.navigateSingleTopTo(route = screen.route)
                },
                screenRoute = currentBackStack?.destination?.route,
            )
        },
        modifier = Modifier.padding(top = 40.dp), // ToDo Need to delete it, after edge to edge fix
    ) { innerPadding ->
        RallyNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
        )
    }
}
