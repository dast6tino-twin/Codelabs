package ru.dast_6_tino.diceroller.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.dast_6_tino.diceroller.ui.composables.DiceWithButtonAndImageStateful
import ru.dast_6_tino.diceroller.ui.theme.DiceRollerTheme

@DarkLightPreviews
@Composable
fun DiceRollerApp() = DiceRollerTheme {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        DiceWithButtonAndImageStateful(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
        )
    }
}
