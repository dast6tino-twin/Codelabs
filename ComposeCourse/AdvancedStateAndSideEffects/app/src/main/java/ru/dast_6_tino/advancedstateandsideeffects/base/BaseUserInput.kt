package ru.dast_6_tino.advancedstateandsideeffects.base

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import ru.dast_6_tino.advancedstateandsideeffects.ui.CraneTheme
import ru.dast_6_tino.advancedstateandsideeffects.ui.captionTextStyle
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.advancedstateandsideeffects.R
import ru.dast_6_tino.advancedstateandsideeffects.ui.DarkLightPreviews

@Composable
fun SimpleUserInput(
    text: String? = null,
    caption: String? = null,
    @DrawableRes vectorImageId: Int? = null,
) = CraneUserInput(
    caption = if (text == null) caption else null,
    text = text.orEmpty(),
    vectorImageId = vectorImageId,
)

@Composable
fun CraneUserInput(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    caption: String? = null,
    @DrawableRes vectorImageId: Int? = null,
    tint: Color = LocalContentColor.current,
) = CraneBaseUserInput(
    modifier = modifier,
    onClick = onClick,
    caption = caption,
    vectorImageId = vectorImageId,
    tintIcon = text.isNotEmpty(),
    tint = tint,
) {
    Text(text = text, style = MaterialTheme.typography.body1.copy(color = tint))
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CraneBaseUserInput(
    tintIcon: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
    caption: String? = null,
    @DrawableRes vectorImageId: Int? = null,
    showCaption: Boolean = true,
    tint: Color = LocalContentColor.current,
    content: @Composable () -> Unit = {},
) = Surface(
    modifier = modifier,
    onClick = onClick,
    color = MaterialTheme.colors.primaryVariant,
) {
    Row(Modifier.padding(all = 12.dp)) {
        if (vectorImageId != null) {
            Icon(
                modifier = Modifier.size(24.dp, 24.dp),
                painter = painterResource(id = vectorImageId),
                tint = if (tintIcon) tint else Color(0x80FFFFFF),
                contentDescription = null,
            )
            Spacer(Modifier.width(8.dp))
        }
        if (caption != null && showCaption) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = caption,
                style = (captionTextStyle).copy(color = tint),
            )
            Spacer(Modifier.width(8.dp))
        }
        Row(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically),
        ) {
            content.invoke()
        }
    }
}

@DarkLightPreviews
@Composable
fun PreviewInput() = CraneTheme {
    Surface {
        CraneBaseUserInput(
            tintIcon = true,
            vectorImageId = R.drawable.ic_plane,
            caption = "Caption",
            showCaption = true,
        ) {
            Text(text = "text", style = MaterialTheme.typography.body1)
        }
    }
}
