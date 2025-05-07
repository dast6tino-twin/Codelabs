package ru.dast_6_tino.basiclayouts.ui.screens.main

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import ru.dast_6_tino.basiclayouts.BasicLayoutsAppScreen
import ru.dast_6_tino.basiclayouts.ui.DarkLightPreviews
import ru.dast_6_tino.basiclayouts.ui.composables.BasicLayoutsNavigationRail
import ru.dast_6_tino.basiclayouts.ui.screens.home.HomeScreen
import ru.dast_6_tino.basiclayouts.ui.theme.BasicLayoutsTheme

@Composable
fun BasicLayoutsAppLandscape(
    onAlignYourBodyClick: (Int) -> Unit,
    onFavoriteCollectionClick: (Int) -> Unit,
    screen: BasicLayoutsAppScreen,
    onScreenSelected: (BasicLayoutsAppScreen) -> Unit,
) {
    BasicLayoutsTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
        ) {
            Row {
                BasicLayoutsNavigationRail(screen, onScreenSelected)
                HomeScreen(onAlignYourBodyClick, onFavoriteCollectionClick)
            }
        }
    }
}

@DarkLightPreviews
@Composable
fun BasicLayoutsAppLandscapeLightPreview() {
    BasicLayoutsAppLandscape({}, {}, BasicLayoutsAppScreen.SPA) {}
}
