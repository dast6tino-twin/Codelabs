package ru.dast_6_tino.accessibility.ui.article

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import ru.dast_6_tino.accessibility.R
import ru.dast_6_tino.accessibility.data.posts.PostsRepository
import ru.dast_6_tino.accessibility.data.posts.post3
import ru.dast_6_tino.accessibility.model.Post
import ru.dast_6_tino.accessibility.ui.components.InsetAwareTopAppBar
import ru.dast_6_tino.accessibility.ui.theme.JetnewsTheme
import ru.dast_6_tino.accessibility.utils.supportWideScreen

/**
 * Stateful Article Screen that manages state using [produceUiState]
 *
 * @param postId (state) the post to show
 * @param postsRepository data source for this screen
 * @param onBack (event) request back navigation
 */
@Suppress("DEPRECATION") // Allow ViewModelLifecycleScope call
@Composable
fun ArticleScreen(
    postId: String?,
    postsRepository: PostsRepository,
    onBack: () -> Unit,
) = ArticleScreen(
    post = postsRepository.getPost(postId)!!,
    onBack = onBack,
)

/**
 * Stateless Article Screen that displays a single post.
 *
 * @param post (state) item to display
 * @param onBack (event) request navigate back
 */
@Composable
fun ArticleScreen(post: Post, onBack: () -> Unit) {
    var showDialog by rememberSaveable { mutableStateOf(false) }
    if (showDialog) {
        FunctionalityNotAvailablePopup { showDialog = false }
    }

    Scaffold(topBar = {
        ArticleTopBar(post.publication?.name, onBack)
    }) { innerPadding ->
        PostContent(
            post = post,
            modifier = Modifier
                // innerPadding takes into account the top and bottom bar
                .padding(innerPadding)
                // center content in landscape mode
                .supportWideScreen(),
        )
    }
}

/**
 * Display a popup explaining functionality not available.
 *
 * @param onDismiss (event) request the popup be dismissed
 */
@Composable
private fun FunctionalityNotAvailablePopup(onDismiss: () -> Unit) = AlertDialog(
    onDismissRequest = onDismiss,
    text = {
        Text(
            text = "Functionality not available \uD83D\uDE48",
            style = MaterialTheme.typography.bodyMedium
        )
    },
    confirmButton = {
        TextButton(onClick = onDismiss) {
            Text(text = "CLOSE")
        }
    },
)

/**
 * Top bar for article screen.
 *
 * @param name source nae where post was published
 * @param onBack (event) request navigate back
 */
@Composable
fun ArticleTopBar(
    name: String?,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) = InsetAwareTopAppBar(
    title = {
        Text(
            text = "Published in: $name",
            style = MaterialTheme.typography.titleSmall,
            color = LocalContentColor.current,
        )
    },
    navigationIcon = {
        IconButton(onClick = onBack) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.cd_navigate_up),
            )
        }
    },
    modifier = modifier,
)

@Preview("Article screen")
@Preview("Article screen (dark)", uiMode = UI_MODE_NIGHT_YES)
@Preview("Article screen (big font)", fontScale = 1.5f)
@Preview("Article screen (large screen)", device = Devices.PIXEL_C)
@Composable
fun PreviewArticle() = JetnewsTheme {
    ArticleScreen(PostsRepository().getPost(post3.id)!!) {}
}

@Preview("Article top bar")
@Preview("Article top bar (dark)", uiMode = UI_MODE_NIGHT_YES)
@Preview("Article top bar (big font)", fontScale = 1.5f)
@Composable
fun PreviewArticleTopBar() = JetnewsTheme {
    ArticleTopBar("Android developers", {})
}
