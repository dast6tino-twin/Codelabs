package ru.dast_6_tino.basiclayouts.ui.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.basiclayouts.R
import ru.dast_6_tino.basiclayouts.ui.DarkLightPreviews
import ru.dast_6_tino.basiclayouts.ui.theme.BasicLayoutsTheme

@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawableRes: Int,
    @StringRes textRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        onClick = onClick,
        shape = MaterialTheme.shapes.medium, // or RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp),
        ) {
            Image(
                painter = painterResource(drawableRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(80.dp),
            )
            Text(
                text = stringResource(textRes),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@DarkLightPreviews
@Composable
fun FavoriteCollectionCardLightPreview() {
    BasicLayoutsTheme {
        FavoriteCollectionCard(
            drawableRes = R.drawable.fc2_nature_meditations,
            textRes = R.string.fc2_nature_meditations,
            onClick = {},
            modifier = Modifier.padding(8.dp),
        )
    }
}
