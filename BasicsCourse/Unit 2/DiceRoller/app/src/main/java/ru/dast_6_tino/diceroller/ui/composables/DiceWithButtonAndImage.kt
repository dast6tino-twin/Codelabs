package ru.dast_6_tino.diceroller.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.diceroller.Dice
import ru.dast_6_tino.diceroller.R
import ru.dast_6_tino.diceroller.ui.DarkLightPreviews
import ru.dast_6_tino.diceroller.ui.theme.DiceRollerTheme

@Composable
fun DiceWithButtonAndImageStateful(modifier: Modifier = Modifier) {
    var result: Dice by remember { mutableStateOf(Dice.One) }
    DiceWithButtonAndImage(
        dice = result,
        onRoll = { result = (1..6).random().let(Dice::getDiceByValue) },
        modifier = modifier,
    )
}

@Composable
fun DiceWithButtonAndImage(
    dice: Dice,
    onRoll: () -> Unit,
    modifier: Modifier = Modifier,
) = Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
    Image(
        painter = painterResource(dice.image),
        contentDescription = stringResource(dice.contentDescription),
    )
    Spacer(modifier = Modifier.height(16.dp))
    Button(onRoll) {
        Text(stringResource(R.string.roll))
    }
}

@DarkLightPreviews
@Composable
private fun DiceWithButtonAndImagePreview() = DiceRollerTheme {
    Surface {
        DiceWithButtonAndImage(Dice.One, {})
    }
}
