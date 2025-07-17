package ru.dast_6_tino.accessibility.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import ru.dast_6_tino.accessibility.JetnewsApplication

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val appContainer = (application as JetnewsApplication).container
        setContent {
            JetnewsApp(appContainer)
        }
    }

}
