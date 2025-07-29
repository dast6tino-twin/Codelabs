package ru.dast_6_tino.affirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.dast_6_tino.affirmations.data.Datasource
import ru.dast_6_tino.affirmations.ui.DarkLightPreviews
import ru.dast_6_tino.affirmations.ui.composables.AffirmationList
import ru.dast_6_tino.affirmations.ui.theme.AffirmationsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AffirmationsApp() }
    }

}

@DarkLightPreviews
@Composable
fun AffirmationsApp() = AffirmationsTheme {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPaddings ->
        AffirmationList(
            affirmations = Datasource().loadAffirmations(),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings),
        )
    }
}
