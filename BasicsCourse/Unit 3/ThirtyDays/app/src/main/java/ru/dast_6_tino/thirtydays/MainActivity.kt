package ru.dast_6_tino.thirtydays

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.thirtydays.data.DaysRepository
import ru.dast_6_tino.thirtydays.model.Day
import ru.dast_6_tino.thirtydays.ui.DarkLightCompactScreenPreviews
import ru.dast_6_tino.thirtydays.ui.DarkLightExpandedScreenPreviews
import ru.dast_6_tino.thirtydays.ui.DarkLightMediumScreenPreviews
import ru.dast_6_tino.thirtydays.ui.composables.DayCard
import ru.dast_6_tino.thirtydays.ui.composables.ThirtyDaysTopBar
import ru.dast_6_tino.thirtydays.ui.theme.ThirtyDaysTheme

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)
            ThirtyDaysApp(windowSizeClass)
        }
    }

}

@Composable
fun ThirtyDaysApp(
    windowSizeClass: WindowSizeClass,
    days: List<Day> = DaysRepository.getDays(),
) = ThirtyDaysTheme {
    Scaffold(topBar = {
        ThirtyDaysTopBar()
    }, modifier = Modifier.fillMaxSize()) { innerPadding ->
        val dayCardModifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_small))
            .let { modifier ->
                when (windowSizeClass.widthSizeClass) {
                    WindowWidthSizeClass.Compact -> modifier.fillMaxWidth()
                    WindowWidthSizeClass.Medium ->
                        modifier.widthIn(max = dimensionResource(R.dimen.medium_column_max_width))

                    else -> modifier.widthIn(max = dimensionResource(R.dimen.expanded_column_max_width))
                }
            }
        LazyColumn(
            contentPadding = PaddingValues(dimensionResource(R.dimen.padding_medium)),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            items(days) { day ->
                DayCard(
                    title = day.title,
                    message = day.message,
                    image = day.image,
                    modifier = dayCardModifier,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@DarkLightCompactScreenPreviews
@Composable
private fun ThirtyDaysAppCompactPreview() = ThirtyDaysApp(
    windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(320.dp, 320.dp)),
)

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@DarkLightMediumScreenPreviews
@Composable
private fun ThirtyDaysAppMediumPreview() = ThirtyDaysApp(
    windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(630.dp, 630.dp)),
)

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@DarkLightExpandedScreenPreviews
@Composable
private fun ThirtyDaysAppExpandedPreview() = ThirtyDaysApp(
    windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(840.dp, 840.dp)),
)
