package ru.dast_6_tino.basiclayouts.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.basiclayouts.FavoriteCollection
import ru.dast_6_tino.basiclayouts.R
import ru.dast_6_tino.basiclayouts.ui.theme.BasicLayoutsTheme

@Composable
fun FavoriteCollectionsGrid(
    data: List<FavoriteCollection>,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp),
    ) {
        items(data) { item ->
            FavoriteCollectionCard(
                drawableRes = item.drawableRes,
                textRes = item.textRes,
                onClick = { onClick.invoke(item.id) },
                modifier = Modifier.height(80.dp),
            )
        }
    }
}

@Preview(
    name = "Favorite collections grid. Light mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
fun FavoriteCollectionsGridLightPreview() {
    BasicLayoutsTheme {
        FavoriteCollectionsGrid(data = favoriteCollectionsMock, onClick = {})
    }
}

@Preview(
    name = "Favorite collections grid. Night mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun FavoriteCollectionsGridNightPreview() {
    BasicLayoutsTheme {
        FavoriteCollectionsGrid(data = favoriteCollectionsMock, onClick = {})
    }
}

val favoriteCollectionsMock: List<FavoriteCollection>
    get() = List(30) { index ->
        FavoriteCollection(index, R.drawable.ab1_inversions, R.string.ab1_inversions)
    }
