package ru.dast_6_tino.woof.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.dast_6_tino.woof.R
import ru.dast_6_tino.woof.ui.DarkLightPreviews
import ru.dast_6_tino.woof.ui.theme.WoofTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WoofTopBar(modifier: Modifier = Modifier) = CenterAlignedTopAppBar(
    title = {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.ic_woof_logo),
                contentDescription = null,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.image_size))
                    .padding(dimensionResource(R.dimen.padding_small)),
            )
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displayLarge,
            )
        }
    },
    modifier = modifier,
)

@DarkLightPreviews
@Composable
private fun WoofTopBarPreview() = WoofTheme {
    Surface {
        WoofTopBar()
    }
}
