package ru.dast_6_tino.lemonade.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.lemonade.LemonadeSteps
import ru.dast_6_tino.lemonade.ui.composables.LemonTextAndImage
import ru.dast_6_tino.lemonade.ui.composables.LemonadeTopAppBar
import ru.dast_6_tino.lemonade.ui.theme.LemonadeTheme

@DarkLightPreviews
@Composable
fun LemonadeApp() = LemonadeTheme {
    Scaffold(
        topBar = { LemonadeTopAppBar() },
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.tertiaryContainer),
            color = MaterialTheme.colorScheme.background,
        ) {
            val squeezeLemonCount by remember { mutableIntStateOf((2..5).random()) }
            var clickCount by remember { mutableIntStateOf(1) }
            LemonTextAndImage(
                step = getStep(clickCount, squeezeLemonCount),
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentSize(Alignment.Center)
                    .clickable {
                        clickCount = if (clickCount == squeezeLemonCount + 2) {
                            1
                        } else {
                            clickCount + 1
                        }
                    },
            )
        }
    }
}

private fun getStep(clickCount: Int, squeezeLemonCount: Int): LemonadeSteps = when (clickCount) {
    1 -> LemonadeSteps.LemonTree
    in 2..squeezeLemonCount -> LemonadeSteps.Lemon
    squeezeLemonCount + 1 -> LemonadeSteps.FullGlass
    squeezeLemonCount + 2 -> LemonadeSteps.EmptyGlass
    else -> throw IllegalArgumentException("Unknown clickCount. $clickCount")
}
