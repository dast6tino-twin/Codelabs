package ru.dast_6_tino.businesscard.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.businesscard.ui.DarkLightPreviews
import ru.dast_6_tino.businesscard.ui.theme.BusinessCardTheme

@Composable
fun MainScreen() = Scaffold(Modifier.fillMaxSize()) { innerPadding ->
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
    ) {
        UserInfo(
            modifier = Modifier
                .padding(all = 16.dp)
                .align(Alignment.Center),
        )
        Contacts(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                .align(Alignment.BottomCenter),
        )
    }
}

@DarkLightPreviews
@Composable
fun MainScreenPreview() = BusinessCardTheme {
    MainScreen()
}
