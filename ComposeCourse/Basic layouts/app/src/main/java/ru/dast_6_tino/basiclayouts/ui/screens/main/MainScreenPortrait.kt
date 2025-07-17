package ru.dast_6_tino.basiclayouts.ui.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.dast_6_tino.basiclayouts.BasicLayoutsAppScreen
import ru.dast_6_tino.basiclayouts.ui.DarkLightPreviews
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

@DarkLightPreviews
@Composable
fun BasicLayoutsAppPortraitLightPreview() {
    BasicLayoutsAppPortrait({}, {}, BasicLayoutsAppScreen.SPA) {}
}
