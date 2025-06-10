package ru.dast_6_tino.advancedstateandsideeffects.base

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import ru.dast_6_tino.advancedstateandsideeffects.home.CraneScreen
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.os.ConfigurationCompat
import ru.dast_6_tino.advancedstateandsideeffects.R

@Composable
fun CraneTabBar(
    modifier: Modifier = Modifier,
    onMenuClicked: () -> Unit,
    children: @Composable (Modifier) -> Unit,
) = Row(modifier) {
    // Separate Row as the children shouldn't have the padding
    Row(Modifier.padding(top = 8.dp)) {
        Image(
            modifier = Modifier
                .padding(top = 8.dp)
                .clickable(onClick = onMenuClicked),
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = stringResource(id = R.string.cd_menu),
        )
        Spacer(Modifier.width(8.dp))
        Image(
            painter = painterResource(id = R.drawable.ic_crane_logo),
            contentDescription = null,
        )
    }
    children.invoke(
        Modifier
            .weight(1f)
            .align(Alignment.CenterVertically),
    )
}

@Composable
fun CraneTabs(
    modifier: Modifier = Modifier,
    titles: List<String>,
    tabSelected: CraneScreen,
    onTabSelected: (CraneScreen) -> Unit,
) = TabRow(
    selectedTabIndex = tabSelected.ordinal,
    modifier = modifier,
    contentColor = MaterialTheme.colors.onSurface,
    indicator = { },
    divider = { },
) {
    titles.forEachIndexed { index, title ->
        val selected = index == tabSelected.ordinal

        val textModifier = if (selected) {
            Modifier
                .border(BorderStroke(2.dp, Color.White), RoundedCornerShape(16.dp))
                .padding(vertical = 8.dp, horizontal = 16.dp)
        } else {
            Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        }

        Tab(
            selected = selected,
            onClick = { onTabSelected(CraneScreen.entries[index]) },
        ) {
            Text(
                modifier = textModifier,
                text = title.uppercase(
                    locale = ConfigurationCompat.getLocales(LocalConfiguration.current)[0]!!,
                ),
            )
        }
    }
}
