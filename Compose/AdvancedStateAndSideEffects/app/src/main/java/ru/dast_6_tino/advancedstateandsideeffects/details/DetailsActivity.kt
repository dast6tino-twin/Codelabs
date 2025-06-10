package ru.dast_6_tino.advancedstateandsideeffects.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.MapView
import com.google.android.libraries.maps.model.LatLng
import com.google.maps.android.ktx.addMarker
import com.google.maps.android.ktx.awaitMap
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.dast_6_tino.advancedstateandsideeffects.base.Result
import ru.dast_6_tino.advancedstateandsideeffects.data.ExploreModel
import ru.dast_6_tino.advancedstateandsideeffects.ui.CraneTheme

internal const val KEY_ARG_DETAILS_CITY_NAME = "KEY_ARG_DETAILS_CITY_NAME"

fun launchDetailsActivity(context: Context, item: ExploreModel) {
    context.startActivity(createDetailsActivityIntent(context, item))
}

@VisibleForTesting
fun createDetailsActivityIntent(context: Context, item: ExploreModel): Intent {
    val intent = Intent(context, DetailsActivity::class.java)
    intent.putExtra(KEY_ARG_DETAILS_CITY_NAME, item.city.name)
    return intent
}

@AndroidEntryPoint
class DetailsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            CraneTheme {
                Surface {
                    DetailsScreen(
                        onErrorLoading = { finish() },
                        modifier = Modifier.systemBarsPadding(),
                    )
                }
            }
        }
    }

}

@Composable
fun DetailsScreen(
    onErrorLoading: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = viewModel(),
) {
    val uiState by produceState(initialValue = DetailsUiState(isLoading = true)) {
        // In a coroutine, this can call suspend functions or move
        // the computation to different Dispatchers
        val cityDetailsResult = viewModel.cityDetails
        value = if (cityDetailsResult is Result.Success) {
            DetailsUiState(cityDetailsResult.data, isLoading = false)
        } else {
            DetailsUiState(throwError = true, isLoading = false)
        }
    }

    when {
        uiState.isLoading -> Box(modifier = modifier) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.align(Alignment.Center),
            )
        }

        uiState.cityDetails != null -> {
            DetailsContent(uiState.cityDetails!!, modifier.fillMaxSize())
        }

        else -> onErrorLoading.invoke()
    }
}

@Composable
fun DetailsContent(
    exploreModel: ExploreModel,
    modifier: Modifier = Modifier,
) = Column(modifier = modifier, verticalArrangement = Arrangement.Center) {
    Spacer(Modifier.height(32.dp))
    Text(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        text = exploreModel.city.nameToDisplay,
        style = MaterialTheme.typography.h4,
        textAlign = TextAlign.Center,
    )
    Text(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        text = exploreModel.description,
        style = MaterialTheme.typography.h6,
        textAlign = TextAlign.Center,
    )
    Spacer(Modifier.height(16.dp))
    CityMapView(exploreModel.city.latitude, exploreModel.city.longitude)
}

@Composable
private fun CityMapView(latitude: String, longitude: String) {
    // The MapView lifecycle is handled by this composable. As the MapView also needs to be updated
    // with input from Compose UI, those updates are encapsulated into the MapViewContainer
    // composable. In this way, when an update to the MapView happens, this composable won't
    // recompose and the MapView won't need to be recreated.
    val mapView = rememberMapViewWithLifecycle()
    MapViewContainer(mapView, latitude, longitude)
}

@Composable
private fun MapViewContainer(map: MapView, latitude: String, longitude: String) {
    val cameraPosition = remember(latitude, longitude) {
        LatLng(latitude.toDouble(), longitude.toDouble())
    }

    LaunchedEffect(map) {
        val googleMap = map.awaitMap()
        googleMap.addMarker { position(cameraPosition) }
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(cameraPosition))
    }

    var zoom by rememberSaveable(map) { mutableFloatStateOf(InitialZoom) }
    ZoomControls(zoom) {
        zoom = it.coerceIn(MinZoom, MaxZoom)
    }

    val coroutineScope = rememberCoroutineScope()
    AndroidView({ map }) { mapView ->
        // Reading zoom so that AndroidView recomposes when it changes. The getMapAsync lambda
        // is stored for later, Compose doesn't recognize state reads
        val mapZoom = zoom
        coroutineScope.launch {
            val googleMap = mapView.awaitMap()
            googleMap.setZoom(mapZoom)
            // Move camera to the same place to trigger the zoom update
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(cameraPosition))
        }
    }
}

@Composable
private fun ZoomControls(
    zoom: Float,
    onZoomChanged: (Float) -> Unit,
) = Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
    ZoomButton("-", onClick = { onZoomChanged(zoom * 0.8f) })
    ZoomButton("+", onClick = { onZoomChanged(zoom * 1.2f) })
}

@Composable
private fun ZoomButton(text: String, onClick: () -> Unit) = Button(
    modifier = Modifier.padding(8.dp),
    colors = ButtonDefaults.buttonColors(
        backgroundColor = MaterialTheme.colors.onPrimary,
        contentColor = MaterialTheme.colors.primary,
    ),
    onClick = onClick,
) {
    Text(text = text, style = MaterialTheme.typography.h5)
}

private const val InitialZoom = 5f
const val MinZoom = 2f
const val MaxZoom = 20f
