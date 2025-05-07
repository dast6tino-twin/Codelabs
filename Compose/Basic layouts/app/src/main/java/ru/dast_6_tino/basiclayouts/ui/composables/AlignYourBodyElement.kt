package ru.dast_6_tino.basiclayouts.ui.composables

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.basiclayouts.R
import ru.dast_6_tino.basiclayouts.ui.theme.BasicLayoutsTheme

@Composable
fun AlignYourBodyElement(
    @DrawableRes drawableRes: Int,
    @StringRes textRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(drawableRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape),
        )
        Text(
            text = stringResource(textRes),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.paddingFromBaseline(top = 24.dp, bottom = 8.dp),
        )
    }
}

@Preview(
    name = "Align your body element. Light mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
fun AlignYourBodyElementLightPreview() {
    BasicLayoutsTheme {
        AlignYourBodyElement(
            onClick = {},
            drawableRes = R.drawable.ab1_inversions,
            textRes = R.string.ab1_inversions,
            modifier = Modifier.padding(8.dp),
        )
    }
}

@Preview(
    name = "Align your body element. Night mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun AlignYourBodyElementNightPreview() {
    BasicLayoutsTheme {
        AlignYourBodyElement(
            onClick = {},
            drawableRes = R.drawable.ab1_inversions,
            textRes = R.string.ab1_inversions,
            modifier = Modifier.padding(8.dp),
        )
    }
}
