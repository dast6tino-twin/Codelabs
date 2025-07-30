package ru.dast_6_tino.courses.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.courses.R
import ru.dast_6_tino.courses.model.Topic
import ru.dast_6_tino.courses.ui.DarkLightPreviews
import ru.dast_6_tino.courses.ui.theme.CoursesTheme

@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) = Card(
    modifier = modifier.height(68.dp),
) {
    Row {
        val title = stringResource(topic.title)
        Image(
            painter = painterResource(topic.image),
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxHeight(),
        )
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = title,
                maxLines = 1,
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(painterResource(R.drawable.ic_grain), null)
                Spacer(Modifier.width(8.dp))
                Text(
                    text = topic.goal.toString(),
                    maxLines = 1,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
    }
}

@DarkLightPreviews
@Composable
private fun TopicCardPreview() = CoursesTheme {
    Surface {
        TopicCard(
            topic = Topic(R.drawable.architecture, R.string.architecture, 58),
            modifier = Modifier.padding(16.dp),
        )
    }
}
