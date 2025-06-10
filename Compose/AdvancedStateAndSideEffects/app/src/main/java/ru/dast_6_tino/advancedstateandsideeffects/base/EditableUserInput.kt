package ru.dast_6_tino.advancedstateandsideeffects.base

import androidx.annotation.DrawableRes
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.SolidColor
import ru.dast_6_tino.advancedstateandsideeffects.ui.captionTextStyle

@Composable
fun CraneEditableUserInput(
    state: EditableUserInputState = rememberEditableUserInputState(""),
    caption: String? = null,
    @DrawableRes vectorImageId: Int? = null,
) {
    val isHint = state.isHint
    CraneBaseUserInput(
        caption = caption,
        tintIcon = !isHint,
        showCaption = !isHint,
        vectorImageId = vectorImageId,
    ) {
        BasicTextField(
            value = state.text,
            onValueChange = state::updateText,
            textStyle = if (isHint) {
                captionTextStyle.copy(color = LocalContentColor.current)
            } else {
                MaterialTheme.typography.body1.copy(color = LocalContentColor.current)
            },
            cursorBrush = SolidColor(LocalContentColor.current),
        )
    }
}

@Composable
fun rememberEditableUserInputState(
    hint: String,
): EditableUserInputState = rememberSaveable(hint, saver = EditableUserInputState.Saver) {
    EditableUserInputState(hint, hint)
}

class EditableUserInputState(private val hint: String, initialText: String) {

    var text: String by mutableStateOf(initialText)
        private set

    val isHint: Boolean
        get() = text == hint

    fun updateText(value: String) {
        text = value
    }

    companion object {

        val Saver: Saver<EditableUserInputState, *> = listSaver(
            save = { state -> listOf(state.hint, state.text) },
            restore = { list ->
                EditableUserInputState(
                    hint = list[0],
                    initialText = list[1],
                )
            },
        )

    }

}
