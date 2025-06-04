package ru.dast_6_tino.basicspractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.dast_6_tino.basicspractice.ui.screens.MainScreen
import ru.dast_6_tino.basicspractice.ui.theme.BasicsPracticeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicsPracticeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        title = stringResource(R.string.main_title),
                        firstText = stringResource(R.string.main_first_text),
                        secondText = stringResource(R.string.main_second_text),
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }

}
