package ru.dast_6_tino.theming.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.dast_6_tino.theming.data.LocalEmailsDataProvider
import ru.dast_6_tino.theming.ui.theme.ReplyTheme

class MainActivity : ComponentActivity() {

    private val viewModel: ReplyHomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val uiState by viewModel.uiState.collectAsState()

            ReplyTheme {
                ReplyApp(
                    replyHomeUIState = uiState,
                    closeDetailScreen = {
                        viewModel.closeDetailScreen()
                    },
                    navigateToDetail = { emailId ->
                        viewModel.setSelectedEmail(emailId)
                    },
                )
            }
        }
    }

}

@DarkLightPreviews
@Composable
fun ReplyAppPreviewLight() {
    ReplyTheme {
        ReplyApp(
            replyHomeUIState = ReplyHomeUIState(
                emails = LocalEmailsDataProvider.allEmails,
            ),
        )
    }
}
