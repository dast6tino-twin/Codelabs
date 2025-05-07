package ru.dast_6_tino.basiclayouts.ui.screens.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.dast_6_tino.basiclayouts.BasicLayoutsAppScreen
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

@Preview(
    name = "Basic layouts app landscape. Light mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
fun BasicLayoutsAppLandscapeLightPreview() {
    BasicLayoutsAppLandscape({}, {}, BasicLayoutsAppScreen.SPA) {}
}

@Preview(
    name = "Basic layouts app landscape. Night mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun BasicLayoutsAppLandscapeNightPreview() {
    BasicLayoutsAppLandscape({}, {}, BasicLayoutsAppScreen.SPA) {}
}
