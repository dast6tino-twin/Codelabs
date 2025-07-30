package ru.dast_6_tino.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.dast_6_tino.courses.data.DataSource
import ru.dast_6_tino.courses.model.Topic
import ru.dast_6_tino.courses.ui.DarkLightPreviews
import ru.dast_6_tino.courses.ui.composable.TopicsGrid
import ru.dast_6_tino.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { CoursesApp() }
    }
}

@DarkLightPreviews
@Composable
fun CoursesApp(
    topics: List<Topic> = DataSource.getTopics(),
) = CoursesTheme {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        TopicsGrid(topics, Modifier.padding(innerPadding))
    }
}
