package ru.dast_6_tino.accessibility.ui.interests

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import ru.dast_6_tino.accessibility.R
import ru.dast_6_tino.accessibility.data.interests.InterestsRepository
import ru.dast_6_tino.accessibility.data.interests.TopicSelection
import ru.dast_6_tino.accessibility.data.interests.TopicsMap
import ru.dast_6_tino.accessibility.ui.components.InsetAwareTopAppBar
import ru.dast_6_tino.accessibility.ui.theme.JetnewsTheme

/**
 * Stateful InterestsScreen that handles the interaction with the repository
 *
 * @param interestsRepository data source for this screen
 * @param openDrawer (event) request opening the app drawer
 */
@Composable
fun InterestsScreen(
    interestsRepository: InterestsRepository,
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier,
) {
    // Returns a [CoroutineScope] that is scoped to the lifecycle of [InterestsScreen]. When this
    // screen is removed from composition, the scope will be cancelled.
    val coroutineScope = rememberCoroutineScope()

    // collectAsState will read a [Flow] in Compose
    val selectedTopics by interestsRepository.observeTopicsSelected().collectAsState(setOf())
    val onTopicSelect: (TopicSelection) -> Unit = {
        coroutineScope.launch { interestsRepository.toggleTopicSelection(it) }
    }
    InterestsScreen(
        topics = interestsRepository.topics,
        selectedTopics = selectedTopics,
        onTopicSelect = onTopicSelect,
        openDrawer = openDrawer,
        modifier = modifier,
    )
}

/**
 * Stateless interest screen displays the topics the user can subscribe to
 *
 * @param topics (state) topics to display, mapped by section
 * @param selectedTopics (state) currently selected topics
 * @param onTopicSelect (event) request a topic selection be changed
 * @param openDrawer (event) request opening the app drawer
 */
@Composable
fun InterestsScreen(
    topics: TopicsMap,
    selectedTopics: Set<TopicSelection>,
    onTopicSelect: (TopicSelection) -> Unit,
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier,
) = Scaffold(topBar = {
    InterestsTopBar(openDrawer)
}) { padding ->
    LazyColumn(
        modifier = modifier.padding(padding),
    ) {
        topics.forEach { (section, topics) ->
            item {
                Text(
                    text = section,
                    modifier = Modifier
                        .padding(16.dp),
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            items(topics) { topic ->
                TopicItem(
                    itemTitle = topic,
                    isSelected = selectedTopics.contains(TopicSelection(section, topic)),
                    onToggle = { onTopicSelect(TopicSelection(section, topic)) },
                )
                Divider(
                    modifier = Modifier.padding(start = 90.dp),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
                )
            }
        }
    }
}

/**
 * Interests screen top bar
 *
 * @param openDrawer (event) request opening the app drawer
 */
@Composable
private fun InterestsTopBar(
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier,
) = InsetAwareTopAppBar(
    title = { Text("Interests") },
    navigationIcon = {
        IconButton(onClick = openDrawer) {
            Icon(
                painter = painterResource(R.drawable.ic_jetnews_logo),
                contentDescription = stringResource(R.string.cd_open_navigation_drawer),
            )
        }
    },
    modifier = modifier,
)

/**
 * Display a full-width topic item
 *
 * @param itemTitle (state) topic title
 * @param isSelected (state) is topic currently selected
 * @param onToggle (event) toggle selection for topic
 */
@Composable
private fun TopicItem(
    itemTitle: String,
    isSelected: Boolean,
    onToggle: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val stateNotSubscribed = stringResource(R.string.state_not_subscribed)
    val stateSubscribed = stringResource(R.string.state_subscribed)
    Row(
        modifier = modifier
            .semantics {
                stateDescription = if (isSelected) stateSubscribed else stateNotSubscribed
            }
            .toggleable(
                value = isSelected,
                onValueChange = { _ -> onToggle.invoke() },
                role = Role.Checkbox,
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Image(
            painter = painterResource(R.drawable.placeholder_1_1),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(56.dp, 56.dp)
                .clip(RoundedCornerShape(4.dp)),
        )
        Text(
            text = itemTitle,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(16.dp),
            style = MaterialTheme.typography.titleMedium,
        )
        Spacer(Modifier.weight(1f))
        Checkbox(
            checked = isSelected,
            onCheckedChange = null,
            modifier = Modifier.align(Alignment.CenterVertically),
        )
    }
}

@Preview("Interests screen", "Interests")
@Preview("Interests screen (dark)", "Interests", uiMode = UI_MODE_NIGHT_YES)
@Preview("Interests screen (big font)", "Interests", fontScale = 1.5f)
@Preview("Interests screen (large screen)", "Interests", device = Devices.PIXEL_C)
@Composable
fun PreviewInterestsScreen() = JetnewsTheme {
    InterestsScreen(
        interestsRepository = InterestsRepository(),
        openDrawer = {},
    )
}

@Preview("Interests top bar", "Top bar")
@Preview("Interests top bar (dark)", "Top bar", uiMode = UI_MODE_NIGHT_YES)
@Preview("Interests top bar (big font)", "Top bar", fontScale = 1.5f)
@Composable
fun PreviewInterestsTopBar() = JetnewsTheme {
    Surface {
        InterestsTopBar({})
    }
}

@Preview("Topic item", "Topic item")
@Preview("Topic item (large screen)", "Topic item", device = Devices.PIXEL_C)
@Composable
fun PreviewTopicItem() = JetnewsTheme {
    Surface {
        TopicItem("Item title", true, { })
    }
}
