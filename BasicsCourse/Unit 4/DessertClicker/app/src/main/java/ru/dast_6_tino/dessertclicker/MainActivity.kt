package ru.dast_6_tino.dessertclicker

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import ru.dast_6_tino.dessertclicker.composables.DessertClickerAppBar
import ru.dast_6_tino.dessertclicker.data.Datasource
import ru.dast_6_tino.dessertclicker.model.Dessert
import ru.dast_6_tino.dessertclicker.screen.DessertClickerScreen
import ru.dast_6_tino.dessertclicker.ui.theme.DessertClickerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate Called")
        enableEdgeToEdge()
        setContent {
            DessertClickerTheme {
                DessertClickerApp(desserts = Datasource.getDessertList())
            }
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart Called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy Called")
    }

    private companion object {

        const val TAG = "MainActivity"

    }

}

/**
 * Determine which dessert to show.
 */
private fun determineDessertToShow(
    desserts: List<Dessert>,
    dessertsSold: Int,
): Dessert {
    var dessertToShow = desserts.first()
    for (dessert in desserts) {
        if (dessertsSold >= dessert.startProductionAmount) {
            dessertToShow = dessert
        } else {
            // The list of desserts is sorted by startProductionAmount. As you sell more desserts,
            // you'll start producing more expensive desserts as determined by startProductionAmount
            // We know to break as soon as we see a dessert who's "startProductionAmount" is greater
            // than the amount sold.
            break
        }
    }

    return dessertToShow
}

/**
 * Share desserts sold information using ACTION_SEND intent
 */
private fun shareSoldDessertsInformation(intentContext: Context, dessertsSold: Int, revenue: Int) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(
            /* name = */ Intent.EXTRA_TEXT,
            /* value = */ intentContext.getString(R.string.share_text, dessertsSold, revenue),
        )
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)

    try {
        intentContext.startActivity(shareIntent)
    } catch (_: ActivityNotFoundException) {
        Toast.makeText(
            /* context = */ intentContext,
            /* text = */ intentContext.getString(R.string.sharing_not_available),
            /* duration = */ Toast.LENGTH_LONG,
        ).show()
    }
}

@Composable
private fun DessertClickerApp(desserts: List<Dessert>) {
    var revenue by rememberSaveable { mutableIntStateOf(0) }
    var dessertsSold by rememberSaveable { mutableIntStateOf(0) }

    val currentDessertIndex by rememberSaveable { mutableIntStateOf(0) }

    var currentDessertPrice by rememberSaveable {
        mutableIntStateOf(desserts[currentDessertIndex].price)
    }
    var currentDessertImageId by rememberSaveable {
        mutableIntStateOf(desserts[currentDessertIndex].imageId)
    }

    Scaffold(topBar = {
        val intentContext = LocalContext.current
        DessertClickerAppBar(onShareButtonClicked = {
            shareSoldDessertsInformation(
                intentContext = intentContext,
                dessertsSold = dessertsSold,
                revenue = revenue,
            )
        })
    }) { contentPadding ->
        DessertClickerScreen(
            revenue = revenue,
            dessertsSold = dessertsSold,
            dessertImageId = currentDessertImageId,
            onDessertClicked = {
                // Update the revenue
                revenue += currentDessertPrice
                dessertsSold++

                // Show the next dessert
                val dessertToShow = determineDessertToShow(desserts, dessertsSold)
                currentDessertImageId = dessertToShow.imageId
                currentDessertPrice = dessertToShow.price
            },
            modifier = Modifier.padding(contentPadding),
        )
    }
}

@Preview
@Composable
private fun MyDessertClickerAppPreview() = DessertClickerTheme {
    DessertClickerApp(listOf(Dessert(R.drawable.cupcake, 5, 0)))
}
