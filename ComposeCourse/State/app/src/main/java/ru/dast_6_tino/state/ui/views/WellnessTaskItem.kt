package ru.dast_6_tino.state.ui.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.state.ui.DarkLightPreviews
import ru.dast_6_tino.state.ui.theme.StateTheme

@Composable
fun WellnessTaskItem(
    taskName: String,
    isChecked: Boolean,
    onCheckClicked: (Boolean) -> Unit,
    onCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val textModifier = Modifier
            .weight(1f)
            .padding(16.dp)
        Text(
            text = taskName,
            modifier = textModifier,
        )
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckClicked,
        )
        IconButton(onClick = onCloseClick) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}

@DarkLightPreviews
@Composable
fun WellnessTaskItemNightPreview() {
    StateTheme {
        WellnessTaskItem(taskName = "Task #2", isChecked = true, onCheckClicked = {}, onCloseClick = {})
    }
}
