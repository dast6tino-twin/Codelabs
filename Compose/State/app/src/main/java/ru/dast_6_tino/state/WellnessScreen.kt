package ru.dast_6_tino.state

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.dast_6_tino.state.ui.theme.StateTheme
import ru.dast_6_tino.state.ui.views.WaterCounterStateful
import ru.dast_6_tino.state.ui.views.WellnessTasksList

@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    viewModel: WellnessViewModel = viewModel(),
) {
    Column(modifier) {
        WaterCounterStateful()
        WellnessTasksList(
            list = viewModel.task,
            onCheckClicked = viewModel::onCheckClicked,
            onCloseClick = viewModel::removeTask,
        )
    }
}

@Preview(
    name = "WellnessScreen. Light mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
fun WellnessScreenLightPreview() {
    StateTheme {
        WellnessScreen()
    }
}

@Preview(
    name = "WellnessScreen. Night mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun WellnessScreenNightPreview() {
    StateTheme {
        WellnessScreen()
    }
}
