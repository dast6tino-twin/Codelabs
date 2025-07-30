package ru.dast_6_tino.courses.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.courses.R
import ru.dast_6_tino.courses.model.Topic
import ru.dast_6_tino.courses.ui.DarkLightPreviews
import ru.dast_6_tino.courses.ui.theme.CoursesTheme

@Composable
fun TopicsGrid(
    topics: List<Topic>,
    modifier: Modifier = Modifier,
) = LazyVerticalGrid(
    columns = GridCells.Fixed(2),
    contentPadding = PaddingValues(8.dp),
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp),
    modifier = modifier,
) {
    items(topics) { topic ->
        TopicCard(topic, Modifier.fillMaxWidth())
    }
}

@DarkLightPreviews
@Composable
private fun TopicsGridPreview() = CoursesTheme {
    Surface {
        TopicsGrid(
            topics = listOf(
                Topic(R.drawable.architecture, R.string.architecture, 58),
                Topic(R.drawable.architecture, R.string.architecture, 58),
                Topic(R.drawable.architecture, R.string.architecture, 58),
                Topic(R.drawable.architecture, R.string.architecture, 58),
                Topic(R.drawable.architecture, R.string.architecture, 58),
                Topic(R.drawable.architecture, R.string.architecture, 58),
            ),
        )
    }
}
