package ru.dast_6_tino.thirtydays.ui

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Dark mode",
    group = "UI mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
)
@Preview(
    name = "Light mode",
    group = "UI mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
)
annotation class DarkLightPreviews

@Preview(
    name = "Compact dark mode",
    group = "UI mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
)
@Preview(
    name = "Compact light mode",
    group = "UI mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
)
annotation class DarkLightCompactScreenPreviews

@Preview(
    name = "Medium dark mode",
    group = "UI mode",
    widthDp = 600,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
)
@Preview(
    name = "Medium light mode",
    group = "UI mode",
    widthDp = 600,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
)
annotation class DarkLightMediumScreenPreviews

@Preview(
    name = "Expanded dark mode",
    group = "UI mode",
    widthDp = 840,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
)
@Preview(
    name = "Expanded light mode",
    group = "UI mode",
    widthDp = 840,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL,
)
annotation class DarkLightExpandedScreenPreviews
