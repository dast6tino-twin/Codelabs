package ru.dast_6_tino.advancedstateandsideeffects.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint
import ru.dast_6_tino.advancedstateandsideeffects.details.launchDetailsActivity
import ru.dast_6_tino.advancedstateandsideeffects.ui.CraneTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            CraneTheme {
                MainScreen { model ->
                    launchDetailsActivity(context = this, item = model)
                }
            }
        }
    }

}

@Composable
private fun MainScreen(onExploreItemClicked: OnExploreItemClicked) = Surface(color = MaterialTheme.colors.primary) {
    var showLandingScreen by remember { mutableStateOf(true) }
    if (showLandingScreen) {
        LandingScreen(onTimeout = { showLandingScreen = false })
    } else {
        CraneHome(onExploreItemClicked = onExploreItemClicked)
    }
}
