package ru.dast_6_tino.basiclayouts

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import ru.dast_6_tino.basiclayouts.ui.screens.main.BasicLayoutsApp

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val windowSize = calculateWindowSizeClass(this)
            BasicLayoutsApp({ id ->
                Toast.makeText(baseContext, "Align your body item clicked. Id = $id", Toast.LENGTH_SHORT).show()
            }, { id ->
                Toast.makeText(baseContext, "Favorite collection item clicked. Id = $id", Toast.LENGTH_SHORT).show()
            }, windowSize)
        }
    }

}
