package ru.dast_6_tino.basiclayouts.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.dast_6_tino.basiclayouts.R
import ru.dast_6_tino.basiclayouts.ui.DarkLightPreviews
import ru.dast_6_tino.basiclayouts.ui.composables.AlignYourBodyRow
import ru.dast_6_tino.basiclayouts.ui.composables.FavoriteCollectionsGrid
import ru.dast_6_tino.basiclayouts.ui.composables.HomeSection
import ru.dast_6_tino.basiclayouts.ui.composables.SearchBar
import ru.dast_6_tino.basiclayouts.ui.theme.BasicLayoutsTheme

@Composable
fun HomeScreen(
    onAlignYourBodyClick: (Int) -> Unit,
    onFavoriteCollectionClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
    ) {
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        val state = viewModel.state
        HomeSection(R.string.align_your_body) {
            AlignYourBodyRow(state.alignYourBodyData, onAlignYourBodyClick)
        }
        HomeSection(R.string.favorite_collections) {
            FavoriteCollectionsGrid(state.favoriteCollectionData, onFavoriteCollectionClick)
        }
        Spacer(Modifier.height(16.dp))
    }
}

@DarkLightPreviews
@Composable
fun HomeScreenLightPreview() {
    BasicLayoutsTheme {
        HomeScreen({}, {})
    }
}
