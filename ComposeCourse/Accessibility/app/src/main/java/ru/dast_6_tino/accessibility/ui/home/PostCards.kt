package ru.dast_6_tino.accessibility.ui.home

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.*
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.accessibility.R
import ru.dast_6_tino.accessibility.data.posts.post1
import ru.dast_6_tino.accessibility.data.posts.post3
import ru.dast_6_tino.accessibility.model.Post
import ru.dast_6_tino.accessibility.ui.theme.JetnewsTheme

@Composable
fun PostCardHistory(post: Post, navigateToArticle: (String) -> Unit) {
    var openDialog by remember { mutableStateOf(false) }
    val onClickLabel = stringResource(R.string.action_read_article)
    val showFewerLabel = stringResource(R.string.cd_show_fewer)
    Row(
        modifier = Modifier
            .clickable(onClickLabel = onClickLabel) {
                navigateToArticle(post.id)
            }
            .semantics {
                customActions = listOf(
                    CustomAccessibilityAction(
                        label = showFewerLabel,
                        // Action returns boolean to indicate success
                        action = {
                            openDialog = true
                            true
                        }
                    ),
                )
            },
    ) {
        Image(
            painter = painterResource(post.imageThumbId),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .size(40.dp, 40.dp)
                .clip(MaterialTheme.shapes.small),
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp, bottom = 16.dp),
        ) {
            Text(post.title, style = MaterialTheme.typography.titleMedium)
            Row(Modifier.padding(top = 4.dp)) {
                CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
                    val textStyle = MaterialTheme.typography.bodyMedium
                    Text(
                        text = post.metadata.author.name,
                        style = textStyle,
                    )
                    Text(
                        text = " - ${post.metadata.readTimeMinutes} min read",
                        style = textStyle,
                    )
                }
            }
        }
        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurfaceVariant) {
            IconButton(
                modifier = Modifier.clearAndSetSemantics {},
                onClick = { openDialog = true },
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = showFewerLabel,
                )
            }
        }
    }
    if (openDialog) {
        AlertDialog(
            modifier = Modifier.padding(20.dp),
            onDismissRequest = { openDialog = false },
            title = {
                Text(
                    text = stringResource(id = R.string.fewer_stories),
                    style = MaterialTheme.typography.titleLarge,
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.fewer_stories_content),
                    style = MaterialTheme.typography.bodyLarge,
                )
            },
            confirmButton = {
                Text(
                    text = stringResource(id = R.string.agree),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .padding(15.dp)
                        .clickable { openDialog = false },
                )
            },
        )
    }
}

@Composable
fun PostCardPopular(
    post: Post,
    navigateToArticle: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val readArticleLabel = stringResource(id = R.string.action_read_article)
    Card(
        colors = CardDefaults.cardColors(),
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .size(280.dp, 240.dp)
            .semantics { onClick(label = readArticleLabel, action = null) },
        onClick = { navigateToArticle(post.id) },
        elevation = CardDefaults.elevatedCardElevation(),
    ) {
        Column {
            Image(
                painter = painterResource(post.imageId),
                contentDescription = null, // decorative
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = post.title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = post.metadata.author.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                )

                Text(
                    text = stringResource(
                        id = R.string.home_post_min_read,
                        formatArgs = arrayOf(
                            post.metadata.date,
                            post.metadata.readTimeMinutes,
                        )
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Preview("Regular colors")
@Preview("Dark colors", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewPostCardPopular() = JetnewsTheme {
    Surface {
        PostCardPopular(post1, {})
    }
}

@Preview("Post History card")
@Composable
fun HistoryPostPreview() = JetnewsTheme {
    Surface {
        PostCardHistory(post3, {})
    }
}
