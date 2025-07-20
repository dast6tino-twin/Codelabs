package ru.dast_6_tino.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ru.dast_6_tino.diceroller.ui.DiceRollerApp
import ru.dast_6_tino.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceRollerApp()
        }
    }

}
