package ru.dast_6_tino.dessertclicker.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import ru.dast_6_tino.dessertclicker.R
import ru.dast_6_tino.dessertclicker.ui.DarkLightPreviews
import ru.dast_6_tino.dessertclicker.ui.theme.DessertClickerTheme

@Composable
fun TransactionInfo(
    revenue: Int,
    dessertsSold: Int,
    modifier: Modifier = Modifier,
) = Column(modifier = modifier) {
    val paddingMedium = dimensionResource(R.dimen.padding_medium)
    DessertsSoldInfo(
        dessertsSold = dessertsSold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingMedium),
    )
    RevenueInfo(
        revenue = revenue,
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingMedium),
    )
}

@Composable
private fun DessertsSoldInfo(dessertsSold: Int, modifier: Modifier = Modifier) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceBetween,
) {
    Text(
        text = stringResource(R.string.dessert_sold),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSecondaryContainer,
    )
    Text(
        text = dessertsSold.toString(),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onSecondaryContainer,
    )
}

@Composable
private fun RevenueInfo(revenue: Int, modifier: Modifier = Modifier) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceBetween,
) {
    Text(
        text = stringResource(R.string.total_revenue),
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.onSecondaryContainer,
    )
    Text(
        text = "$${revenue}",
        textAlign = TextAlign.Right,
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.onSecondaryContainer,
    )
}

@DarkLightPreviews
@Composable
private fun TransactionInfoPreview() = DessertClickerTheme {
    Surface {
        TransactionInfo(300, 300)
    }
}
