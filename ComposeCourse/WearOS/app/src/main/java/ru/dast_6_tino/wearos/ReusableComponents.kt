package ru.dast_6_tino.wearos

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Message
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.SelfImprovement
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import androidx.wear.compose.material3.*
import androidx.wear.compose.material3.lazy.rememberTransformationSpec
import androidx.wear.compose.material3.lazy.transformedHeight
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import com.google.android.horologist.compose.layout.ColumnItemType
import com.google.android.horologist.compose.layout.rememberResponsiveColumnPadding
import ru.dast_6_tino.wearos.theme.WearAppTheme

/* Contains individual Wear OS demo composables for the code lab. */

@Composable
fun IconButtonExample(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) = FilledIconButton(
    onClick = onClick,
    modifier = modifier,
) {
    Icon(
        imageVector = Icons.Rounded.Phone,
        contentDescription = stringResource(R.string.triggers_phone_action),
    )
}

@Composable
fun TextExample(
    transformation: SurfaceTransformation,
    modifier: Modifier = Modifier,
) = ListHeader(modifier = modifier, transformation = transformation) {
    Text(
        text = stringResource(R.string.hello_compose_codelab),
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
fun CardExample(
    onClick: () -> Unit,
    transformation: SurfaceTransformation,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier,
) = AppCard(
    appImage = {
        Icon(
            imageVector = Icons.AutoMirrored.Rounded.Message,
            contentDescription = stringResource(R.string.triggers_open_message_action),
            modifier = iconModifier,
        )
    },
    appName = {
        Text(stringResource(R.string.card_example_title))
    },
    time = {
        Text(stringResource(R.string.card_example_time))
    },
    title = {
        Text(stringResource(R.string.card_example_author))
    },
    onClick = onClick,
    transformation = transformation,
    modifier = modifier,
) {
    Text(stringResource(R.string.card_example_message))
}

@Composable
fun ChipExample(
    onClick: () -> Unit,
    transformation: SurfaceTransformation,
    modifier: Modifier = Modifier,
) = Button(
    icon = {
        Icon(
            imageVector = Icons.Rounded.SelfImprovement,
            contentDescription = stringResource(R.string.triggers_meditation_action),
        )
    },
    onClick = onClick,
    transformation = transformation,
    modifier = modifier,
) {
    Text(
        text = stringResource(R.string._5_minute_meditation),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
fun SwitchChipExample(transformation: SurfaceTransformation, modifier: Modifier = Modifier) {
    var checked by remember { mutableStateOf(false) }
    val onText = stringResource(R.string.on)
    val offText = stringResource(R.string.off)
    SwitchButton(
        label = {
            Text(
                text = stringResource(R.string.sound),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.semantics {
                    contentDescription = if (checked) onText else offText
                },
            )
        },
        checked = checked,
        onCheckedChange = { checked = !checked },
        enabled = true,
        transformation = transformation,
        modifier = modifier,
    )
}

// Button Preview
@WearPreviewDevices
@Composable
fun ButtonExamplePreview() = WearAppTheme {
    AppScaffold {
        val listState = rememberTransformingLazyColumnState()
        val contentPadding = rememberResponsiveColumnPadding(first = ColumnItemType.IconButton)
        ScreenScaffold(
            scrollState = listState,
            contentPadding = contentPadding,
        ) { contentPadding ->
            TransformingLazyColumn(state = listState, contentPadding = contentPadding) {
                item {
                    IconButtonExample({})
                }
            }
        }
    }
}

// Text Preview
@WearPreviewDevices
@Composable
fun TextExamplePreview() = WearAppTheme {
    AppScaffold {
        val listState = rememberTransformingLazyColumnState()
        val transformationSpec = rememberTransformationSpec()
        val contentPadding = rememberResponsiveColumnPadding(first = ColumnItemType.BodyText)
        ScreenScaffold(
            scrollState = listState,
            contentPadding = contentPadding,
        ) { contentPadding ->
            TransformingLazyColumn(state = listState, contentPadding = contentPadding) {
                item {
                    TextExample(
                        transformation = SurfaceTransformation(transformationSpec),
                        modifier = Modifier
                            .fillMaxWidth()
                            .transformedHeight(this, transformationSpec),
                    )
                }
            }
        }
    }
}

// Card Preview
@WearPreviewDevices
@Composable
fun CardExamplePreview() = WearAppTheme {
    AppScaffold {
        val listState = rememberTransformingLazyColumnState()
        val transformationSpec = rememberTransformationSpec()
        val contentPadding = rememberResponsiveColumnPadding(first = ColumnItemType.Card)
        ScreenScaffold(
            scrollState = listState,
            contentPadding = contentPadding,
        ) { contentPadding ->
            TransformingLazyColumn(state = listState, contentPadding = contentPadding) {
                item {
                    CardExample(
                        onClick = {},
                        transformation = SurfaceTransformation(transformationSpec),
                        modifier = Modifier
                            .fillMaxWidth()
                            .transformedHeight(this, transformationSpec),
                    )
                }
            }
        }
    }
}

// Chip Preview
@WearPreviewDevices
@Composable
fun ChipPreview() = WearAppTheme {
    AppScaffold {
        val listState = rememberTransformingLazyColumnState()
        val transformationSpec = rememberTransformationSpec()
        val contentPadding = rememberResponsiveColumnPadding(first = ColumnItemType.Button)
        ScreenScaffold(
            scrollState = listState,
            contentPadding = contentPadding,
        ) { contentPadding ->
            TransformingLazyColumn(state = listState, contentPadding = contentPadding) {
                item {
                    ChipExample(
                        onClick = {},
                        transformation = SurfaceTransformation(transformationSpec),
                        modifier = Modifier
                            .fillMaxWidth()
                            .transformedHeight(this, transformationSpec),
                    )
                }
            }
        }
    }
}

// Switch Chip Preview
@WearPreviewDevices
@Composable
fun SwitchChipExamplePreview() = WearAppTheme {
    AppScaffold {
        val listState = rememberTransformingLazyColumnState()
        val transformationSpec = rememberTransformationSpec()
        val contentPadding = rememberResponsiveColumnPadding(first = ColumnItemType.Button)
        ScreenScaffold(
            scrollState = listState,
            contentPadding = contentPadding,
        ) { contentPadding ->
            TransformingLazyColumn(state = listState, contentPadding = contentPadding) {
                item {
                    SwitchChipExample(
                        transformation = SurfaceTransformation(transformationSpec),
                        modifier = Modifier
                            .fillMaxWidth()
                            .transformedHeight(this, transformationSpec),
                    )
                }
            }
        }
    }
}
