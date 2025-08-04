package ru.dast_6_tino.thirtydays.ui.composables

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import ru.dast_6_tino.thirtydays.R
import ru.dast_6_tino.thirtydays.ui.DarkLightPreviews
import ru.dast_6_tino.thirtydays.ui.theme.ThirtyDaysTheme
import ru.dast_6_tino.thirtydays.ui.theme.transparent

@Composable
fun DayCard(
    @StringRes title: Int,
    @StringRes message: Int,
    @DrawableRes image: Int,
    modifier: Modifier = Modifier,
) {
    var isExpanded by remember { mutableStateOf(false) }
    DayCardStateless(title, message, image, isExpanded, { isExpanded = !isExpanded }, modifier)
}

@Composable
fun DayCardStateless(
    @StringRes title: Int,
    @StringRes message: Int,
    @DrawableRes image: Int,
    isExpanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) = Card(
    shape = MaterialTheme.shapes.medium,
    elevation = CardDefaults.cardElevation(dimensionResource(R.dimen.card_elevation)),
    modifier = modifier,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick.invoke() }
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessMedium,
                ),
            ),
    ) {
        val paddingMedium = dimensionResource(R.dimen.padding_medium)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(paddingMedium),
        ) {
            Text(
                text = stringResource(title),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(Modifier.weight(1f))

            val rotationAngle by animateFloatAsState(
                targetValue = if (isExpanded) 180f else 0f,
                animationSpec = tween(500),
                label = "rotationAnimation",
            )
            Icon(
                imageVector = Icons.Default.ExpandMore,
                contentDescription = null,
                modifier = Modifier.graphicsLayer { rotationZ = rotationAngle },
            )
        }

        if (isExpanded) {
            DayMessageImage(message, image)
        }
    }
}

@Composable
private fun DayMessageImage(
    @StringRes message: Int,
    @DrawableRes image: Int,
    modifier: Modifier = Modifier,
) = Box(
    modifier = modifier.clip(MaterialTheme.shapes.small),
) {
    Image(
        painter = painterResource(image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.image_height)),
    )
    val backgroundColor = MaterialTheme.colorScheme.surfaceVariant
    val textBackground = Brush.verticalGradient(
        colors = listOf(
            backgroundColor,
            backgroundColor.copy(0.8f),
            backgroundColor.copy(0.6f),
            transparent,
        )
    )
    Text(
        text = stringResource(message),
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth()
            .background(textBackground)
            .padding(dimensionResource(R.dimen.padding_medium)),
    )
}

@DarkLightPreviews
@Composable
private fun DayCardPreview() = ThirtyDaysTheme {
    Surface {
        DayCardStateless(
            title = R.string.day_one_title,
            message = R.string.day_one_message,
            image = R.drawable.image_day_1,
            isExpanded = false,
            onClick = {},
        )
    }
}

@DarkLightPreviews
@Composable
private fun DayCardExpandedPreview() = ThirtyDaysTheme {
    Surface {
        DayCardStateless(
            title = R.string.day_one_title,
            message = R.string.day_one_message,
            image = R.drawable.image_day_1,
            isExpanded = true,
            onClick = {},
        )
    }
}

@DarkLightPreviews
@Composable
fun DayMessageImagePreview() = ThirtyDaysTheme {
    Surface {
        DayMessageImage(
            message = R.string.day_one_message,
            image = R.drawable.image_day_1,
        )
    }
}
