package ru.dast_6_tino.advancedstateandsideeffects.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay
import ru.dast_6_tino.advancedstateandsideeffects.R

private const val SplashWaitTime: Long = 2000

@Composable
fun LandingScreen(onTimeout: () -> Unit, modifier: Modifier = Modifier) = Box(
    modifier = modifier.fillMaxSize(),
    contentAlignment = Alignment.Center,
) {
    // This will always refer to the latest onTimeout function that
    // LandingScreen was recomposed with
    val currentOnTimeout by rememberUpdatedState(onTimeout)

    // Create an effect that matches the lifecycle of LandingScreen.
    // If LandingScreen recomposes or onTimeout changes,
    // the delay shouldn't start again.
    LaunchedEffect(Unit) {
        delay(SplashWaitTime) // Loading simulation
        currentOnTimeout.invoke()
    }
    Image(painterResource(id = R.drawable.ic_crane_drawer), contentDescription = null)
}
