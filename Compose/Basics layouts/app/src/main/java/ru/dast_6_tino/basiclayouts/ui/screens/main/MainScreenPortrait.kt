package ru.dast_6_tino.basiclayouts.ui.screens.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.dast_6_tino.basiclayouts.BasicLayoutsAppScreen
import ru.dast_6_tino.basiclayouts.ui.composables.BasicLayoutsBottomNavigation
import ru.dast_6_tino.basiclayouts.ui.screens.home.HomeScreen
import ru.dast_6_tino.basiclayouts.ui.theme.BasicLayoutsTheme

@Composable
fun BasicLayoutsAppPortrait(
    onAlignYourBodyClick: (Int) -> Unit,
    onFavoriteCollectionClick: (Int) -> Unit,
    screen: BasicLayoutsAppScreen,
    onScreenSelected: (BasicLayoutsAppScreen) -> Unit,
) {
    BasicLayoutsTheme {
        Scaffold(
            bottomBar = { BasicLayoutsBottomNavigation(screen, onScreenSelected) },
        ) { padding ->
            HomeScreen(
                onAlignYourBodyClick = onAlignYourBodyClick,
                onFavoriteCollectionClick = onFavoriteCollectionClick,
                modifier = Modifier.padding(padding),
            )
        }
    }
}

@Preview(
    name = "Basic layouts app portrait. Light mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
fun BasicLayoutsAppPortraitLightPreview() {
    BasicLayoutsAppPortrait({}, {}, BasicLayoutsAppScreen.SPA) {}
}

@Preview(
    name = "Basic layouts app portrait. Night mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun BasicLayoutsAppPortraitNightPreview() {
    BasicLayoutsAppPortrait({}, {}, BasicLayoutsAppScreen.SPA) {}
}
