package ru.dast_6_tino.wearos.theme

import androidx.compose.runtime.Composable
import androidx.wear.compose.material3.MaterialTheme

@Composable
fun WearAppTheme(
    content: @Composable () -> Unit,
) = MaterialTheme(
    colorScheme = WearAppColorPalette,
    typography = WearAppTypography,
    // For shapes, we generally recommend using the default Material Wear shapes which are
    // optimized for round and non-round devices.
    content = content,
)

