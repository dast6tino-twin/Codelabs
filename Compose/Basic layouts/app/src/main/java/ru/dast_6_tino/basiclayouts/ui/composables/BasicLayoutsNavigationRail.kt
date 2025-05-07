package ru.dast_6_tino.basiclayouts.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.basiclayouts.BasicLayoutsAppScreen
import ru.dast_6_tino.basiclayouts.R
import ru.dast_6_tino.basiclayouts.ui.theme.BasicLayoutsTheme

@Composable
fun BasicLayoutsNavigationRail(
    screen: BasicLayoutsAppScreen,
    onScreenSelected: (BasicLayoutsAppScreen) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationRail(
        modifier = modifier.padding(horizontal = 8.dp),
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight(),
        ) {
            NavigationRailItem(
                icon = {
                    Icon(imageVector = Icons.Default.Spa, contentDescription = null)
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_home))
                },
                selected = screen == BasicLayoutsAppScreen.SPA,
                onClick = { onScreenSelected.invoke(BasicLayoutsAppScreen.SPA) },
            )
            Spacer(Modifier.height(8.dp))
            NavigationRailItem(
                icon = {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
                },
                label = {
                    Text(stringResource(R.string.bottom_navigation_profile))
                },
                selected = screen == BasicLayoutsAppScreen.PROFILE,
                onClick = { onScreenSelected.invoke(BasicLayoutsAppScreen.PROFILE) },
            )
        }
    }
}

@Preview(
    name = "Basic layouts navigation rail. Light mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
fun BasicLayoutsNavigationRailLightPreview() {
    BasicLayoutsTheme {
        BasicLayoutsNavigationRail(BasicLayoutsAppScreen.SPA, {})
    }
}

@Preview(
    name = "Basic layouts navigation rail. Night mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun BasicLayoutsNavigationRailNightPreview() {
    BasicLayoutsTheme {
        BasicLayoutsNavigationRail(BasicLayoutsAppScreen.PROFILE, {})
    }
}
