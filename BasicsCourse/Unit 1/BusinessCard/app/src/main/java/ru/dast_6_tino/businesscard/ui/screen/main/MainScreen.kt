package ru.dast_6_tino.businesscard.ui.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.dast_6_tino.businesscard.ui.DarkLightPreviews
import ru.dast_6_tino.businesscard.ui.composable.Contacts
import ru.dast_6_tino.businesscard.ui.composable.UserInfo
import ru.dast_6_tino.businesscard.ui.theme.BusinessCardTheme

@Composable
fun MainScreen(
    onPhoneClick: (String) -> Unit,
    onShareClick: (String) -> Unit,
    onEmailClick: (String) -> Unit,
    viewModel: MainViewModel = viewModel(),
) = Scaffold(Modifier.fillMaxSize()) { innerPadding ->
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

        val state by viewModel.stateFlow.collectAsStateWithLifecycle(lifecycle = LocalLifecycleOwner.current.lifecycle)
        val share = state.share
        val email = state.email
        Contacts(
            phone = state.formattedPhone,
            onPhoneClick = { onPhoneClick.invoke(state.phone) },
            share = share,
            onShareClick = { onShareClick.invoke(share) },
            email = email,
            onEmailClick = { onEmailClick.invoke(email) },
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                .align(Alignment.BottomCenter),
        )
    }
}

@DarkLightPreviews
@Composable
fun MainScreenPreview() = BusinessCardTheme {
    MainScreen({}, {}, {})
}
