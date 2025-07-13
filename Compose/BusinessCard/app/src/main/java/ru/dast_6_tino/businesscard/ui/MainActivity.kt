package ru.dast_6_tino.businesscard.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ru.dast_6_tino.businesscard.ui.composable.MainScreen
import ru.dast_6_tino.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            BusinessCardTheme {
                MainScreen()
            }
        }
    }

}
