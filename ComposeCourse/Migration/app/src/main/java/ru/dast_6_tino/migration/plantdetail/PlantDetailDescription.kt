package ru.dast_6_tino.migration.plantdetail

import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import ru.dast_6_tino.migration.DarkLightPreviews
import ru.dast_6_tino.migration.R
import ru.dast_6_tino.migration.data.Plant
import ru.dast_6_tino.migration.theme.SunflowerTheme
import ru.dast_6_tino.migration.viewmodels.PlantDetailViewModel

/**
 * Note from CodeLab:
 *
 * In a production app, a ViewModel should only be referenced by a screen-level composable.
 * If child composables need data from a ViewModel,
 * it is best practice to only pass data that child composables need rather than the whole ViewModel.
 *
 * Composables don't have their own ViewModel instances,
 * the same instance is shared between the composables and the lifecycle owner that hosts that Compose code
 * (either Activity or Fragment).
 **/
@Composable
fun PlantDetailDescription(
    viewModel: PlantDetailViewModel,
    modifier: Modifier = Modifier,
) {
    val plant by viewModel.plant.observeAsState()
    plant?.let {
        PlantDetailContent(it, modifier)
    }
}

// region Detail content
@Composable
private fun PlantDetailContent(plant: Plant, modifier: Modifier = Modifier) {
    Surface(modifier) {
        Column(Modifier.padding(dimensionResource(R.dimen.margin_normal))) {
            PlantName(plant.name)
            PlantWatering(plant.wateringInterval)
            PlantDescription(plant.description)
        }
    }
}

@DarkLightPreviews
@Composable
private fun PlantDetailContentPreview() {
    val plant = Plant("id", "Apple", "HTML<br><br>description", 3, 30, "")
    SunflowerTheme {
        PlantDetailContent(plant)
    }
}
// endregion

// region Name
@Composable
private fun PlantName(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        style = MaterialTheme.typography.headlineSmall,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.margin_small))
            .wrapContentWidth(Alignment.CenterHorizontally),
    )
}

@DarkLightPreviews
@Composable
private fun PlantNamePreview() {
    SunflowerTheme {
        PlantName("Sunflower")
    }
}
// endregion

// region Watering
@Composable
private fun PlantWatering(wateringInterval: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(R.dimen.margin_normal)),
    ) {
        val centerWithPaddingModifier = Modifier
            .padding(horizontal = dimensionResource(R.dimen.margin_small))
            .align(Alignment.CenterHorizontally)

        Text(
            text = stringResource(R.string.watering_needs_prefix),
            color = MaterialTheme.colorScheme.primaryContainer,
            fontWeight = FontWeight.Bold,
            modifier = centerWithPaddingModifier,
        )

        val wateringIntervalText = pluralStringResource(
            R.plurals.watering_needs_suffix,
            wateringInterval,
            wateringInterval,
        )
        Text(
            text = wateringIntervalText,
            modifier = centerWithPaddingModifier,
        )
    }
}

@DarkLightPreviews
@Composable
private fun PlantWateringPreview() {
    SunflowerTheme {
        PlantWatering(0)
    }
}
// endregion

// region Description
@Composable
private fun PlantDescription(description: String, modifier: Modifier = Modifier) {
    // Remembers the HTML formatted description. Re-executes on a new description
    val htmlDescription = remember(description) {
        HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
    }

    // Displays the TextView on the screen and updates with the HTML description when inflated
    // Updates to htmlDescription will make AndroidView recompose and update the text
    AndroidView(
        factory = { context ->
            TextView(context).apply {
                movementMethod = LinkMovementMethod.getInstance()
            }
        },
        update = { textView ->
            textView.text = htmlDescription
        },
        modifier = modifier,
    )
}

@DarkLightPreviews
@Composable
private fun PlantDescriptionPreview() {
    SunflowerTheme {
        PlantDescription("HTML<br><br>description")
    }
}
// endregion
