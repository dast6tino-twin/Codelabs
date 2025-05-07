package ru.dast_6_tino.basiclayouts.ui.screens.main

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.dast_6_tino.basiclayouts.BasicLayoutsAppViewModel

@Composable
fun BasicLayoutsApp(
    onAlignYourBodyClick: (Int) -> Unit,
    onFavoriteCollectionClick: (Int) -> Unit,
    windowSize: WindowSizeClass,
    viewModel: BasicLayoutsAppViewModel = viewModel(),
) {
    val screen = viewModel.screen
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> BasicLayoutsAppPortrait(
            onAlignYourBodyClick = onAlignYourBodyClick,
            onFavoriteCollectionClick = onFavoriteCollectionClick,
            screen = screen,
            onScreenSelected = viewModel::onScreenSelected,
        )

        WindowWidthSizeClass.Medium,
        WindowWidthSizeClass.Expanded -> BasicLayoutsAppLandscape(
            onAlignYourBodyClick = onAlignYourBodyClick,
            onFavoriteCollectionClick = onFavoriteCollectionClick,
            screen = screen,
            onScreenSelected = viewModel::onScreenSelected,
        )
    }
}
