package ru.dast_6_tino.basiclayouts.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.basiclayouts.AlignYourBody
import ru.dast_6_tino.basiclayouts.R
import ru.dast_6_tino.basiclayouts.ui.DarkLightPreviews
import ru.dast_6_tino.basiclayouts.ui.theme.BasicLayoutsTheme

@Composable
fun AlignYourBodyRow(
    data: List<AlignYourBody>,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp),
        modifier = modifier,
    ) {
        items(data) { item ->
            AlignYourBodyElement(
                drawableRes = item.drawableRes,
                textRes = item.textRes,
                onClick = { onClick.invoke(item.id) },
            )
        }
    }
}

@DarkLightPreviews
@Composable
fun AlignYourBodyRowLightPreview() {
    BasicLayoutsTheme {
        AlignYourBodyRow(alignYourBodyMock, {})
    }
}

val alignYourBodyMock: List<AlignYourBody>
    get() = List(30) { index ->
        AlignYourBody(index, R.drawable.fc1_short_mantras, R.string.fc1_short_mantras)
    }
