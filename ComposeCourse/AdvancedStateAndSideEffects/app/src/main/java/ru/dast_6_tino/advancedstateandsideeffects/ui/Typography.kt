package ru.dast_6_tino.advancedstateandsideeffects.ui

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.dast_6_tino.advancedstateandsideeffects.R

private val light = Font(R.font.raleway_light, FontWeight.W300)
private val regular = Font(R.font.raleway_regular, FontWeight.W400)
private val medium = Font(R.font.raleway_medium, FontWeight.W500)
private val semibold = Font(R.font.raleway_semibold, FontWeight.W600)

private val craneFontFamily = FontFamily(fonts = listOf(light, regular, medium, semibold))

val captionTextStyle = TextStyle(
    fontFamily = craneFontFamily,
    fontWeight = FontWeight.W400,
    fontSize = 16.sp,
)

val craneTypography = Typography(
    h1 = TextStyle(
        fontFamily = craneFontFamily,
        fontWeight = FontWeight.W300,
        fontSize = 96.sp,
    ),
    h2 = TextStyle(
        fontFamily = craneFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 60.sp,
    ),
    h3 = TextStyle(
        fontFamily = craneFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 48.sp,
    ),
    h4 = TextStyle(
        fontFamily = craneFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 34.sp,
    ),
    h5 = TextStyle(
        fontFamily = craneFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp,
    ),
    h6 = TextStyle(
        fontFamily = craneFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 20.sp,
    ),
    subtitle1 = TextStyle(
        fontFamily = craneFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
    ),
    subtitle2 = TextStyle(
        fontFamily = craneFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 14.sp,
    ),
    body1 = TextStyle(
        fontFamily = craneFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp,
    ),
    body2 = TextStyle(
        fontFamily = craneFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
    ),
    button = TextStyle(
        fontFamily = craneFontFamily,
        fontWeight = FontWeight.W600,
        fontSize = 14.sp,
    ),
    caption = TextStyle(
        fontFamily = craneFontFamily,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp,
    ),
    overline = TextStyle(
        fontFamily = craneFontFamily,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
    ),
)
