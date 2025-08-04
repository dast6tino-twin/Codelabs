package ru.dast_6_tino.thirtydays.ui.composables

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.dast_6_tino.thirtydays.R
import ru.dast_6_tino.thirtydays.ui.DarkLightPreviews
import ru.dast_6_tino.thirtydays.ui.theme.ThirtyDaysTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThirtyDaysTopBar(modifier: Modifier = Modifier) = TopAppBar(title = {
    Text(stringResource(R.string.app_name), style = MaterialTheme.typography.displayMedium)
}, modifier = modifier)

@DarkLightPreviews
@Composable
private fun TopBarPreview() = ThirtyDaysTheme {
    Surface {
        ThirtyDaysTopBar()
    }
}
