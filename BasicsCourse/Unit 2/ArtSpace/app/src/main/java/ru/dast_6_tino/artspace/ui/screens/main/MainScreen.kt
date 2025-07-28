package ru.dast_6_tino.artspace.ui.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import ru.dast_6_tino.artspace.data.Art
import ru.dast_6_tino.artspace.ui.DarkLightPreviews
import ru.dast_6_tino.artspace.ui.components.*
import ru.dast_6_tino.artspace.ui.theme.ArtSpaceTheme

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel(),
) {
    val coroutineScope = rememberCoroutineScope()
    val art by viewModel.artStateFlow.collectAsStateWithLifecycle(LocalLifecycleOwner.current)

    MainScreenStateless(
        art = art,
        onPreviousClick = {
            coroutineScope.launch { viewModel.previous(art.image) }
        },
        onNextClick = {
            coroutineScope.launch { viewModel.next(art.image) }
        },
    )
}

@Composable
private fun MainScreenStateless(
    art: Art,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
) = ArtSpaceTheme {
    Scaffold(Modifier.fillMaxSize()) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
        ) {
            val image = art.image
            val name = art.name
            HorizontalDraggableColumn(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                onDragEnd = { isNext ->
                    when (isNext) {
                        true -> onNextClick.invoke()
                        false -> onPreviousClick.invoke()
                        else -> Unit
                    }
                },
                modifier = Modifier.weight(1f),
            ) {
                ArtImage(
                    image = image,
                    imageDescription = name,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize()
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                )
                Spacer(Modifier.height(32.dp))
                ArtTitle(
                    name = name,
                    author = art.author,
                    year = art.year,
                    copyright = stringResource(art.copyright),
                    modifier = Modifier.widthIn(max = 800.dp),
                )
            }
            Spacer(Modifier.height(16.dp))
            NavigationRow(
                onPreviousClick = onPreviousClick,
                onNextClick = onNextClick,
                modifier = Modifier.widthIn(max = 500.dp),
            )
        }
    }
}

@DarkLightPreviews
@Composable
private fun MainScreenStatelessPreview() = MainScreenStateless(Art.default, {}, {})
