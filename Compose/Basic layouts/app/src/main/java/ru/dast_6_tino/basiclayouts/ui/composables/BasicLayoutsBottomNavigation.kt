package ru.dast_6_tino.basiclayouts.ui.composables

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.dast_6_tino.basiclayouts.BasicLayoutsAppScreen
import ru.dast_6_tino.basiclayouts.R
import ru.dast_6_tino.basiclayouts.ui.theme.BasicLayoutsTheme

@Composable
fun BasicLayoutsBottomNavigation(
    screen: BasicLayoutsAppScreen,
    onScreenSelected: (BasicLayoutsAppScreen) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        contentColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier,
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Spa,
                    contentDescription = null,
                )
            },
            label = {
                Text(stringResource(R.string.bottom_navigation_home))
            },
            selected = screen == BasicLayoutsAppScreen.SPA,
            onClick = { onScreenSelected.invoke(BasicLayoutsAppScreen.SPA) },
        )
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                )
            },
            label = {
                Text(stringResource(R.string.bottom_navigation_profile))
            },
            selected = screen == BasicLayoutsAppScreen.PROFILE,
            onClick = { onScreenSelected.invoke(BasicLayoutsAppScreen.PROFILE) },
        )
    }
}

@Preview(
    name = "Basic layouts bottom navigation. Light mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
fun BasicLayoutsBottomNavigationLightPreview() {
    BasicLayoutsTheme {
        BasicLayoutsBottomNavigation(BasicLayoutsAppScreen.SPA, {})
    }
}

@Preview(
    name = "Basic layouts bottom navigation. Night mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun BasicLayoutsBottomNavigationNightPreview() {
    BasicLayoutsTheme {
        BasicLayoutsBottomNavigation(BasicLayoutsAppScreen.PROFILE, {})
    }
}
