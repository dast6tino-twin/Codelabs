package ru.dast_6_tino.animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ru.dast_6_tino.animation.ui.AnimationCodelabTheme
import ru.dast_6_tino.animation.ui.home.Home

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimationCodelabTheme {
                Home()
            }
        }
    }

}
