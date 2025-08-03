package ru.dast_6_tino.superheroes.ui.composables

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.dast_6_tino.superheroes.R
import ru.dast_6_tino.superheroes.ui.DarkLightPreviews

@OptIn(ExperimentalMaterial3Api::class)
@DarkLightPreviews
@Composable
fun HeroesAppBar(modifier: Modifier = Modifier) = CenterAlignedTopAppBar(
    title = {
        Text(text = stringResource(R.string.app_name), style = MaterialTheme.typography.displayLarge)
    },
    modifier = modifier,
)
