package ru.dast_6_tino.artspace.ui.components

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun HorizontalDraggableColumn(
    onDragEnd: (Boolean?) -> Unit,
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable (ColumnScope.() -> Unit),
) {
    var offsetX by remember { mutableFloatStateOf(0f) }
    Column(
        modifier = modifier
            .offset { IntOffset(offsetX.roundToInt(), 0) }
            .pointerInput(Unit) {
                detectDragGestures(onDrag = { _, dragAmount ->
                    val original = dragAmount.copy(x = offsetX)
                    val summed = original + dragAmount
                    offsetX = summed.x.coerceIn(-200.dp.toPx(), 200.dp.toPx())
                }, onDragEnd = {
                    when {
                        offsetX > 0 -> onDragEnd.invoke(false)
                        offsetX < 0 -> onDragEnd.invoke(true)
                    }
                    offsetX = 0f
                })
            },
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        content = content,
    )
}
