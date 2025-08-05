package ru.dast_6_tino.dessertclicker.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import ru.dast_6_tino.dessertclicker.R
import ru.dast_6_tino.dessertclicker.ui.DarkLightPreviews
import ru.dast_6_tino.dessertclicker.ui.theme.DessertClickerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DessertClickerAppBar(
    onShareButtonClicked: () -> Unit,
    modifier: Modifier = Modifier,
) = TopAppBar(
    title = {
        Text(
            text = stringResource(R.string.app_name),
            modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_medium)),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleLarge,
        )
    },
    actions = {
        IconButton(
            onClick = onShareButtonClicked,
            modifier = Modifier.padding(end = dimensionResource(R.dimen.padding_medium)),
        ) {
            Icon(
                imageVector = Icons.Filled.Share,
                contentDescription = stringResource(R.string.share),
                tint = MaterialTheme.colorScheme.onPrimary,
            )
        }
    },
    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
    modifier = modifier,
)

@DarkLightPreviews
@Composable
private fun DessertClickerAppBarPreview() = DessertClickerTheme {
    Surface {
        DessertClickerAppBar({}, Modifier.fillMaxWidth())
    }
}