package ru.dast_6_tino.advancedstateandsideeffects.util

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection

@Composable
fun Circle(color: Color) = Canvas(Modifier.fillMaxSize()) {
    drawCircle(color)
}

@Composable
fun SemiRect(color: Color, lookingLeft: Boolean = true) {
    val layoutDirection = LocalLayoutDirection.current
    Canvas(Modifier.fillMaxSize()) {
        // The SemiRect should face left EITHER the lookingLeft param is true
        // OR the layoutDirection is Rtl
        val offset = if (lookingLeft xor (layoutDirection == LayoutDirection.Rtl)) {
            Offset(0f, 0f)
        } else {
            Offset(size.width / 2, 0f)
        }
        val size = Size(width = size.width / 2, height = size.height)

        drawRect(size = size, topLeft = offset, color = color)
    }
}
