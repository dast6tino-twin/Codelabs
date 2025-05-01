package ru.dast_6_tino.state.ui.views

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.state.ui.theme.StateTheme

@Composable
fun WaterCounterStateful(modifier: Modifier = Modifier) {
    var countState by rememberSaveable { mutableIntStateOf(0) }
    WaterCounterStateless(countState, { countState++ }, modifier)
}

@Composable
fun WaterCounterStateless(
    count: Int,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(16.dp)) {
        if (count > 0) {
            Text("You've had $count glasses.")
        }
        Button(
            onClick = onButtonClick,
            modifier = Modifier.padding(top = 8.dp),
            enabled = count < 10,
        ) {
            Text("Add one")
        }
    }
}

@Preview(
    name = "WaterCounter. Light mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
fun WaterCounterLightPreview() {
    StateTheme {
        WaterCounterStateless(count = 2, onButtonClick = {})
    }
}

@Preview(
    name = "WaterCounter. Night mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun WaterCounterNightPreview() {
    StateTheme {
        WaterCounterStateless(count = 2, onButtonClick = {})
    }
}
