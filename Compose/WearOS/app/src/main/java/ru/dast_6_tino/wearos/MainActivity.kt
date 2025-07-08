package ru.dast_6_tino.wearos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.wear.compose.foundation.lazy.TransformingLazyColumn
import androidx.wear.compose.foundation.lazy.rememberTransformingLazyColumnState
import androidx.wear.compose.material3.*
import androidx.wear.compose.material3.lazy.rememberTransformationSpec
import androidx.wear.compose.material3.lazy.transformedHeight
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import com.google.android.horologist.compose.layout.ColumnItemType
import com.google.android.horologist.compose.layout.rememberResponsiveColumnPadding
import ru.dast_6_tino.wearos.theme.WearAppTheme

/**
 * This code lab is meant to help existing Compose developers get up to speed quickly on
 * Compose for Wear OS.
 *
 * The code lab walks through a majority of the simple composables for Wear OS (both similar to
 * existing mobile composables and new composables).
 *
 * It also covers more advanced composables like [TransformingLazyColumn] (Wear OS's version of
 * [LazyColumn]) and the Wear OS version of [androidx.wear.compose.material.Scaffold].The codelab explains the advantage of using
 * [TransformingLazyColumn] and  [AppScaffold] and [ScreenScaffold] to simplify
 * code development to align with Wear OS UX guidance.
 *
 * Check out [this link](https://android-developers.googleblog.com/2021/10/compose-for-wear-os-now-in-developer.html)
 * for more information on Compose for Wear OS.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WearApp()
        }
    }

}

@Composable
fun WearApp() = WearAppTheme {
    /**************************** Part 4: Wear OS Scaffold ****************************/
    AppScaffold {
        val listState = rememberTransformingLazyColumnState()
        val transformationSpec = rememberTransformationSpec()

        /**************************** Part 4: Wear OS Scaffold ****************************/
        ScreenScaffold(
            scrollState = listState,
            contentPadding = rememberResponsiveColumnPadding(
                first = ColumnItemType.IconButton,
                last = ColumnItemType.Button,
            ),
            /**************************** Part 11: EdgeButton ****************************/
            edgeButton = {
                EdgeButton(
                    onClick = {},
                    buttonSize = EdgeButtonSize.Small,
                ) {
                    Text(stringResource(R.string.more))
                }
            },
        ) { contentPaddings ->
            /**************************** Part 3: ScalingLazyColumn ****************************/
            TransformingLazyColumn(
                state = listState,
                contentPadding = contentPaddings,
            ) {
                /**************************** Part 1: Simple composables ****************************/
                item {
                    IconButtonExample(onClick = {})
                }
                item {
                    TextExample(
                        transformation = SurfaceTransformation(transformationSpec),
                        modifier = Modifier
                            .fillMaxWidth()
                            .transformedHeight(this, transformationSpec),
                    )
                }
                item {
                    CardExample(
                        onClick = {},
                        transformation = SurfaceTransformation(transformationSpec),
                        modifier = Modifier
                            .fillMaxWidth()
                            .transformedHeight(this, transformationSpec),
                    )
                }

                /**************************** Part 2: Wear unique composables ****************************/
                item {
                    ChipExample(
                        onClick = {},
                        transformation = SurfaceTransformation(transformationSpec),
                        modifier = Modifier
                            .fillMaxWidth()
                            .transformedHeight(this, transformationSpec),
                    )
                }
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

@WearPreviewDevices
@Composable
fun WearAppPreview() {
    WearApp()
}
