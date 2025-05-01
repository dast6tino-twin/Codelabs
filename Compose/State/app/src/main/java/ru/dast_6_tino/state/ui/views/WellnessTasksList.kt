package ru.dast_6_tino.state.ui.views

import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.dast_6_tino.state.WellnessTask
import ru.dast_6_tino.state.ui.theme.StateTheme

@Composable
fun WellnessTasksList(
    list: List<WellnessTask>,
    onCheckClicked: (Int, Boolean) -> Unit,
    onCloseClick: (WellnessTask) -> Unit,
    modifier: Modifier = Modifier,
) = LazyColumn(modifier) {
    items(
        items = list,
        key = { task -> task.id },
    ) { item ->
        WellnessTaskItem(
            taskName = item.label,
            isChecked = item.isCheckedState,
            onCheckClicked = { value -> onCheckClicked.invoke(item.id, value) },
            onCloseClick = { onCloseClick.invoke(item) },
        )
    }
}

@Preview(
    name = "WellnessTasksList. Light mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
fun WellnessTasksListLightPreview() {
    StateTheme {
        WellnessTasksList(list = previewList(), onCheckClicked = { _, _ -> }, onCloseClick = {})
    }
}

@Preview(
    name = "WellnessTasksList. Night mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun WellnessTasksListNightPreview() {
    StateTheme {
        WellnessTasksList(list = previewList(), onCheckClicked = { _, _ -> }, onCloseClick = {})
    }
}

private fun previewList() = List(3) { index -> WellnessTask(index, "Task #$index") }
