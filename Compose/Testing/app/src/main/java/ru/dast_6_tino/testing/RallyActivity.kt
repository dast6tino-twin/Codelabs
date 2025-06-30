package ru.dast_6_tino.testing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import ru.dast_6_tino.testing.ui.components.RallyTopAppBar
import ru.dast_6_tino.testing.ui.theme.RallyTheme

/**
 * This Activity recreates part of the Rally Material Study from
 * https://material.io/design/material-studies/rally.html
 */
class RallyActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RallyApp()
        }
    }

}

@Composable
fun RallyApp() = RallyTheme {
    var currentScreen by rememberSaveable { mutableStateOf(RallyScreen.Overview) }
    Scaffold(
        topBar = {
            RallyTopAppBar(
                allScreens = RallyScreen.entries,
                onTabSelected = { screen -> currentScreen = screen },
                currentScreen = currentScreen
            )
        },
    ) { innerPadding ->
        Box(Modifier.padding(innerPadding)) {
            currentScreen.content(onScreenChange = { screen -> currentScreen = screen })
        }
    }
}
