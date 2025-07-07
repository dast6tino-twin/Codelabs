package ru.dast_6_tino.adaptiveapps.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.dast_6_tino.adaptiveapps.data.local.LocalEmailsDataProvider
import ru.dast_6_tino.adaptiveapps.ui.theme.ReplyTheme

class MainActivity : ComponentActivity() {

    private val viewModel: ReplyHomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }

        setContent {
            ReplyTheme {
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                ReplyApp(
                    replyHomeUIState = uiState,
                    onEmailClick = viewModel::setSelectedEmail,
                )

                /* Displaying the screen sizes
                val adaptiveInfo = currentWindowAdaptiveInfo()
                val sizeClassText = "width = ${adaptiveInfo.windowSizeClass.windowWidthSizeClass}\n" +
                    "height = ${adaptiveInfo.windowSizeClass.windowHeightSizeClass}"

                Text(
                    text = sizeClassText,
                    color = Color.Magenta,
                    modifier = Modifier.padding(
                        paddingValues = WindowInsets.safeDrawing.asPaddingValues(),
                    ),
                )*/
            }
        }
    }

}

@Preview(showBackground = true)
@Preview(showBackground = true, widthDp = 700)
@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ReplyAppPreviewTablet() = ReplyTheme {
    ReplyApp(
        replyHomeUIState = ReplyHomeUIState(
            emails = LocalEmailsDataProvider.allEmails,
        ),
        onEmailClick = {},
    )
}
