package ru.dast_6_tino.basiclayouts.ui.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.basiclayouts.R
import ru.dast_6_tino.basiclayouts.ui.DarkLightPreviews
import ru.dast_6_tino.basiclayouts.ui.theme.BasicLayoutsTheme

@Composable
fun HomeSection(
    @StringRes titleRes: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(modifier) {
        Text(
            text = stringResource(titleRes),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp),
        )
        content.invoke()
    }
}

@DarkLightPreviews
@Composable
fun HomeSectionLightPreview() {
    BasicLayoutsTheme {
        HomeSection(R.string.align_your_body) {
            AlignYourBodyRow(alignYourBodyMock, {})
        }
    }
}
