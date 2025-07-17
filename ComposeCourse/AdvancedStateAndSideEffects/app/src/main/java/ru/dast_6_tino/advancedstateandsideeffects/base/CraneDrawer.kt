package ru.dast_6_tino.advancedstateandsideeffects.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import ru.dast_6_tino.advancedstateandsideeffects.ui.CraneTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.advancedstateandsideeffects.R
import ru.dast_6_tino.advancedstateandsideeffects.ui.DarkLightPreviews

private val screens = listOf("Find Trips", "My Trips", "Saved Trips", "Price Alerts", "My Account")

@Composable
fun CraneDrawer(modifier: Modifier = Modifier) = Column(
    modifier = modifier
        .fillMaxSize()
        .padding(start = 24.dp, top = 48.dp),
) {
    Image(
        painter = painterResource(R.drawable.ic_crane_drawer),
        contentDescription = stringResource(R.string.cd_drawer),
    )
    for (screen in screens) {
        Spacer(Modifier.height(24.dp))
        Text(text = screen, style = MaterialTheme.typography.h4)
    }
}

@DarkLightPreviews
@Composable
fun CraneDrawerPreview() = CraneTheme {
    CraneDrawer()
}
