package ru.dast_6_tino.accessibility.ui.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.accessibility.data.posts.PostsRepository
import ru.dast_6_tino.accessibility.model.Post
import ru.dast_6_tino.accessibility.ui.components.InsetAwareTopAppBar
import kotlinx.coroutines.launch
import ru.dast_6_tino.accessibility.R
import ru.dast_6_tino.accessibility.ui.theme.JetnewsTheme

/**
 * Stateful HomeScreen which manages state using [produceUiState]
 *
 * @param postsRepository data source for this screen
 * @param navigateToArticle (event) request navigation to Article screen
 * @param openDrawer (event) request opening the app drawer
 */
@Composable
fun HomeScreen(
    postsRepository: PostsRepository,
    navigateToArticle: (String) -> Unit,
    openDrawer: () -> Unit,
) = HomeScreen(
    posts = postsRepository.getPosts(),
    navigateToArticle = navigateToArticle,
    openDrawer = openDrawer,
)

/**
 * Responsible for displaying the Home Screen of this application.
 *
 * Stateless composable is not coupled to any specific state management.
 *
 * @param posts (state) the data to show on the screen
 * @param favorites (state) favorite posts
 * @param onToggleFavorite (event) toggles favorite for a post
 * @param navigateToArticle (event) request navigation to Article screen
 * @param openDrawer (event) request opening the app drawer
 */
@Composable
fun HomeScreen(
    posts: List<Post>,
    navigateToArticle: (String) -> Unit,
    openDrawer: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            val title = stringResource(id = R.string.app_name)
            InsetAwareTopAppBar(
                title = { Text(text = title) },
                navigationIcon = {
                    IconButton(onClick = { coroutineScope.launch { openDrawer() } }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_jetnews_logo),
                            contentDescription = stringResource(R.string.cd_open_navigation_drawer),
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        PostList(posts, navigateToArticle, modifier)
    }
}

/**
 * Display a list of posts.
 *
 * When a post is clicked on, [navigateToArticle] will be called to navigate to the detail screen
 * for that post.
 *
 * @param posts (state) the list to display
 * @param navigateToArticle (event) request navigation to Article screen
 * @param modifier modifier for the root element
 */
@Composable
private fun PostList(
    posts: List<Post>,
    navigateToArticle: (postId: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val postsHistory = posts.subList(0, 3)
    val postsPopular = posts.subList(3, 5)
    val contentPadding = rememberContentPaddingForScreen(additionalTop = 8.dp)
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
    ) {
        items(postsHistory) { post ->
            PostCardHistory(post, navigateToArticle)
            PostListDivider()
        }
        item {
            PostListPopularSection(postsPopular, navigateToArticle)
        }
    }
}

/**
 * Horizontal scrolling cards for [PostList]
 *
 * @param posts (state) to display
 * @param navigateToArticle (event) request navigation to Article screen
 */
@Composable
private fun PostListPopularSection(
    posts: List<Post>,
    navigateToArticle: (String) -> Unit,
) = Column {
    Text(
        modifier = Modifier.padding(16.dp),
        text = stringResource(id = R.string.home_popular_section_title),
        style = MaterialTheme.typography.titleMedium,
    )

    LazyRow(contentPadding = PaddingValues(end = 16.dp)) {
        items(posts) { post ->
            PostCardPopular(
                post = post,
                navigateToArticle = navigateToArticle,
                modifier = Modifier.padding(start = 16.dp, bottom = 16.dp),
            )
        }
    }
    PostListDivider()
}

/**
 * Full-width divider with padding for [PostList]
 */
@Composable
private fun PostListDivider() = Divider(
    modifier = Modifier.padding(horizontal = 14.dp),
    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f),
)

/**
 * Determine the content padding to apply to the different screens of the app
 */
@Composable
fun rememberContentPaddingForScreen(
    additionalTop: Dp = 0.dp,
): PaddingValues = WindowInsets.systemBars
    .only(WindowInsetsSides.Bottom)
    .add(WindowInsets(top = additionalTop))
    .asPaddingValues()

@Preview("Home screen")
@Preview("Home screen (dark)", uiMode = UI_MODE_NIGHT_YES)
@Preview("Home screen (big font)", fontScale = 1.5f)
@Preview("Home screen (large screen)", device = Devices.PIXEL_C)
@Composable
fun PreviewHomeScreen() = JetnewsTheme {
    HomeScreen(
        posts = PostsRepository().getPosts(),
        navigateToArticle = { /*TODO*/ },
        openDrawer = { /*TODO*/ },
    )
}
