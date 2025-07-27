package ru.dast_6_tino.artspace.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.artspace.R
import ru.dast_6_tino.artspace.ui.DarkLightPreviews
import ru.dast_6_tino.artspace.ui.theme.ArtSpaceTheme

@Composable
fun NavigationRow(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier,
) = Row(modifier = modifier) {
    Button(
        onClick = onPreviousClick,
        modifier = Modifier.weight(1f),
    ) {
        Text(stringResource(R.string.action_previous))
    }
    Spacer(Modifier.width(16.dp))
    Button(
        onClick = onNextClick,
        modifier = Modifier.weight(1f),
    ) {
        Text(stringResource(R.string.action_next))
    }
}

@DarkLightPreviews
@Composable
private fun NavigationRowPreview() = ArtSpaceTheme {
    Surface {
        NavigationRow({}, {})
    }
}