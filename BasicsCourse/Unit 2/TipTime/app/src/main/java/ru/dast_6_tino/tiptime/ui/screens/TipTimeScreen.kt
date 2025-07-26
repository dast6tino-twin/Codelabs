package ru.dast_6_tino.tiptime.ui.screens

import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.tiptime.R
import ru.dast_6_tino.tiptime.ui.DarkLightPreviews
import ru.dast_6_tino.tiptime.ui.composables.EditNumberField
import ru.dast_6_tino.tiptime.ui.composables.RoundTheTipRow
import ru.dast_6_tino.tiptime.ui.theme.TipTimeTheme
import java.text.NumberFormat
import kotlin.math.ceil

@Composable
fun TipTimeScreen() = Column(
    modifier = Modifier
        .padding(horizontal = 16.dp)
        .verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
) {
    var amountInput by remember { mutableStateOf("") }
    val amount = amountInput.toDoubleOrNull() ?: 0.0
    var tipPercentInput by remember { mutableStateOf("15") }
    var roundUpTips by remember { mutableStateOf(false) }
    val tips = calculateTip(
        amount = amount,
        tipPercent = tipPercentInput.toDoubleOrNull() ?: 0.0,
        roundUp = roundUpTips,
    )

    Text(
        text = stringResource(R.string.calculate_tip),
        modifier = Modifier
            .padding(bottom = 16.dp, top = 40.dp)
            .align(alignment = Alignment.Start),
    )
    EditNumberField(
        leadIcon = R.drawable.money,
        label = R.string.bill_amount,
        amount = amountInput,
        onAmountChanged = { value -> amountInput = value },
        imeAction = ImeAction.Next,
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth(),
    )
    EditNumberField(
        leadIcon = R.drawable.percent,
        label = R.string.how_was_the_service,
        amount = tipPercentInput,
        onAmountChanged = { value -> tipPercentInput = value },
        imeAction = ImeAction.Done,
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth(),
    )
    RoundTheTipRow(
        round = roundUpTips,
        onRoundChanged = { value -> roundUpTips = value },
        modifier = Modifier.padding(bottom = 32.dp),
    )
    Text(
        text = stringResource(R.string.tip_amount, tips),
        style = MaterialTheme.typography.displaySmall,
    )
    Spacer(modifier = Modifier.height(150.dp))
}

/**
 * Calculates the tip based on the user input and format the tip amount
 * according to the local currency.
 * Example would be "$10.00".
 *
 * @param amount tipping amount
 * @param tipPercent tip percentage
 * @param roundUp rounding flag
 *
 * @return tips
 */
@VisibleForTesting
internal fun calculateTip(amount: Double, tipPercent: Double, roundUp: Boolean): String {
    val tip = tipPercent / 100 * amount
    val roundedTips = if (roundUp) ceil(tip) else tip
    return NumberFormat.getCurrencyInstance().format(roundedTips)
}

@DarkLightPreviews
@Composable
fun TipTimeLayoutPreview() = TipTimeTheme {
    Surface {
        TipTimeScreen()
    }
}
