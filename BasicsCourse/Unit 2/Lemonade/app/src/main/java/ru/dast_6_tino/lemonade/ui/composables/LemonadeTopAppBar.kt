package ru.dast_6_tino.lemonade.ui.composables

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import ru.dast_6_tino.lemonade.R
import ru.dast_6_tino.lemonade.ui.DarkLightPreviews
import ru.dast_6_tino.lemonade.ui.theme.LemonadeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeTopAppBar(modifier: Modifier = Modifier) = CenterAlignedTopAppBar(
    title = {
        Text(
            text = stringResource(R.string.app_name),
            fontWeight = FontWeight.Bold,
        )
    },
    colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
    modifier = modifier,
)

@DarkLightPreviews
@Composable
private fun LemonadeTopAppBarPreview() = LemonadeTheme {
    Surface {
        LemonadeTopAppBar()
    }
}
