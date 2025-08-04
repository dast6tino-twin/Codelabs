package ru.dast_6_tino.thirtydays.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.*
import ru.dast_6_tino.thirtydays.R

private val Carlito = FontFamily(
    Font(R.font.carlito_regular),
    Font(resId = R.font.carlito_bold, weight = FontWeight.Bold),
    Font(resId = R.font.carlito_italic, style = FontStyle.Italic),
    Font(resId = R.font.carlito_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
)

private val ABeeZee = FontFamily(
    Font(resId = R.font.a_bee_zee_regular),
    Font(resId = R.font.a_bee_zee_italic, style = FontStyle.Italic),
)

private val BaseTypography = Typography()

val Typography = Typography().copy(
    displayLarge = BaseTypography.displayLarge.copy(fontFamily = Carlito),
    displayMedium = BaseTypography.displayMedium.copy(fontFamily = Carlito),
    displaySmall = BaseTypography.displaySmall.copy(fontFamily = Carlito),
    headlineLarge = BaseTypography.headlineLarge.copy(fontFamily = Carlito),
    headlineMedium = BaseTypography.headlineMedium.copy(fontFamily = Carlito),
    headlineSmall = BaseTypography.headlineSmall.copy(fontFamily = Carlito),
    titleLarge = BaseTypography.titleLarge.copy(fontFamily = Carlito),
    titleMedium = BaseTypography.titleMedium.copy(fontFamily = Carlito),
    titleSmall = BaseTypography.titleSmall.copy(fontFamily = Carlito),
    bodyLarge = BaseTypography.bodyLarge.copy(fontFamily = ABeeZee),
    bodyMedium = BaseTypography.bodyMedium.copy(fontFamily = ABeeZee),
    bodySmall = BaseTypography.bodySmall.copy(fontFamily = ABeeZee),
    labelLarge = BaseTypography.labelLarge.copy(fontFamily = ABeeZee),
    labelMedium = BaseTypography.labelMedium.copy(fontFamily = ABeeZee),
    labelSmall = BaseTypography.labelSmall.copy(fontFamily = ABeeZee),
)
