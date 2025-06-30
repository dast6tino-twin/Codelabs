package ru.dast_6_tino.testing.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.testing.ui.theme.RallyDialogThemeOverlay

@Composable
fun RallyAlertDialog(
    onDismiss: () -> Unit,
    bodyText: String,
    buttonText: String,
) = RallyDialogThemeOverlay {
    AlertDialog(
        onDismissRequest = onDismiss,
        text = { Text(bodyText) },
        buttons = {
            Column {
                Divider(
                    Modifier.padding(horizontal = 12.dp),
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
                )
                TextButton(
                    onClick = onDismiss,
                    shape = RectangleShape,
                    contentPadding = PaddingValues(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(buttonText)
                }
            }
        },
    )
}
