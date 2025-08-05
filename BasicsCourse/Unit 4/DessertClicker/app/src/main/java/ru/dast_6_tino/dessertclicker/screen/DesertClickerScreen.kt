package ru.dast_6_tino.dessertclicker.screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import ru.dast_6_tino.dessertclicker.R
import ru.dast_6_tino.dessertclicker.composables.TransactionInfo
import ru.dast_6_tino.dessertclicker.ui.DarkLightPreviews
import ru.dast_6_tino.dessertclicker.ui.theme.DessertClickerTheme

@Composable
fun DessertClickerScreen(
    revenue: Int,
    dessertsSold: Int,
    @DrawableRes dessertImageId: Int,
    onDessertClicked: () -> Unit,
    modifier: Modifier = Modifier,
) = Box(modifier = modifier) {
    Image(
        painter = painterResource(R.drawable.bakery_back),
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
    Column {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        ) {
            Image(
                painter = painterResource(dessertImageId),
                contentDescription = null,
                modifier = Modifier
                    .width(dimensionResource(R.dimen.image_size))
                    .height(dimensionResource(R.dimen.image_size))
                    .align(Alignment.Center)
                    .clickable { onDessertClicked.invoke() },
                contentScale = ContentScale.Crop,
            )
        }
        TransactionInfo(
            revenue = revenue,
            dessertsSold = dessertsSold,
            modifier = Modifier.background(MaterialTheme.colorScheme.secondaryContainer),
        )
    }
}

@DarkLightPreviews
@Composable
private fun DessertClickerScreenPreview() = DessertClickerTheme {
    Surface {
        DessertClickerScreen(300, 300, R.drawable.cupcake, {})
    }
}
