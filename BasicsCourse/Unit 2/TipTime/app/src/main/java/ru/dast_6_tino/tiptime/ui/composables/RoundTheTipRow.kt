package ru.dast_6_tino.tiptime.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.tiptime.R
import ru.dast_6_tino.tiptime.ui.DarkLightPreviews
import ru.dast_6_tino.tiptime.ui.theme.TipTimeTheme

@Composable
fun RoundTheTipRow(
    round: Boolean,
    onRoundChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) = Row(
    modifier = modifier
        .fillMaxWidth()
        .size(48.dp),
    verticalAlignment = Alignment.CenterVertically,
) {
    Text(stringResource(R.string.round_up_tip))
    Switch(
        checked = round,
        onCheckedChange = onRoundChanged,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.End),
    )
}

@DarkLightPreviews
@Composable
fun RoundTheTipRowCheckedPreview() = TipTimeTheme {
    Surface {
        RoundTheTipRow(true, {})
    }
}

@DarkLightPreviews
@Composable
fun RoundTheTipRowUncheckedPreview() = TipTimeTheme {
    Surface {
        RoundTheTipRow(false, {})
    }
}
