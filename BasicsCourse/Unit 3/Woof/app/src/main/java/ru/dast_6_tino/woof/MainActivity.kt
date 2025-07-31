package ru.dast_6_tino.woof

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import ru.dast_6_tino.woof.data.dogs
import ru.dast_6_tino.woof.ui.DarkLightPreviews
import ru.dast_6_tino.woof.ui.composables.DogItem
import ru.dast_6_tino.woof.ui.composables.WoofTopBar
import ru.dast_6_tino.woof.ui.theme.WoofTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WoofApp()
        }
    }

}

/**
 * Composable that displays an app bar and a list of dogs.
 */
@DarkLightPreviews
@Composable
fun WoofApp() = WoofTheme {
    Scaffold(topBar = {
        WoofTopBar()
    }) { innerPaddings ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPaddings),
        ) {
            LazyColumn(
                contentPadding = PaddingValues(dimensionResource(R.dimen.padding_small)),
            ) {
                items(dogs) { dog ->
                    DogItem(
                        dog = dog,
                        modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_small)),
                    )
                }
            }
        }
    }
}
