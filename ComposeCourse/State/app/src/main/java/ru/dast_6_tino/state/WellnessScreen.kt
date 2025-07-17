package ru.dast_6_tino.state

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.dast_6_tino.state.ui.DarkLightPreviews
import ru.dast_6_tino.state.ui.theme.StateTheme
import ru.dast_6_tino.state.ui.views.WaterCounterStateful
import ru.dast_6_tino.state.ui.views.WellnessTasksList

@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    viewModel: WellnessViewModel = viewModel(),
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column {
            WaterCounterStateful()
            WellnessTasksList(
                list = viewModel.task,
                onCheckClicked = viewModel::onCheckClicked,
                onCloseClick = viewModel::removeTask,
            )
        }
    }
}

@DarkLightPreviews
@Composable
fun WellnessScreenNightPreview() {
    StateTheme {
        WellnessScreen()
    }
}
