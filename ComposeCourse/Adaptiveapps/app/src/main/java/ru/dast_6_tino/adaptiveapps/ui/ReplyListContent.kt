package ru.dast_6_tino.adaptiveapps.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.adaptiveapps.data.Email
import ru.dast_6_tino.adaptiveapps.R

@Composable
fun ReplyListPane(
    replyHomeUIState: ReplyHomeUIState,
    onEmailClick: (Email) -> Unit,
    modifier: Modifier = Modifier,
) = LazyColumn(
    modifier = modifier.fillMaxWidth(),
    contentPadding = WindowInsets.safeDrawing.only(
        sides = WindowInsetsSides.Horizontal + WindowInsetsSides.Top,
    ).asPaddingValues(),
) {
    item {
        ReplySearchBar(modifier = Modifier.fillMaxWidth())
    }
    items(replyHomeUIState.emails) { email ->
        ReplyEmailListItem(
            email = email,
            onEmailClick = onEmailClick,
        )
    }
}

@Composable
fun ReplyDetailPane(email: Email, modifier: Modifier = Modifier) {
    val layoutDirection = LocalLayoutDirection.current

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = WindowInsets.safeDrawing.only(
            sides = WindowInsetsSides.Horizontal + WindowInsetsSides.Top,
        ).asPaddingValues()
    ) {
        item {
            ReplyEmailThreadItem(email)
        }
        items(email.replies) { reply ->
            ReplyEmailThreadItem(reply)
        }
    }
}

@Composable
fun ReplyEmailListItem(
    email: Email,
    onEmailClick: (Email) -> Unit,
    modifier: Modifier = Modifier,
) = Card(
    onClick = { onEmailClick(email) },
    modifier = modifier.padding(horizontal = 16.dp, vertical = 4.dp),
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            ReplyProfileImage(
                drawableResource = email.sender.avatar,
                description = email.sender.fullName,
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = email.sender.firstName,
                    style = MaterialTheme.typography.labelMedium,
                )
                Text(
                    text = email.createAt,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.outline,
                )
            }
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surface),
            ) {
                Icon(
                    imageVector = Icons.Default.StarBorder,
                    contentDescription = "Favorite",
                    tint = MaterialTheme.colorScheme.outline,
                )
            }
        }

        Text(
            text = email.subject,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
        )
        Text(
            text = email.body,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun ReplyEmailThreadItem(email: Email, modifier: Modifier = Modifier) = Card(
    modifier = modifier.padding(horizontal = 16.dp),
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            ReplyProfileImage(
                drawableResource = email.sender.avatar,
                description = email.sender.fullName,
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = email.sender.firstName,
                    style = MaterialTheme.typography.labelMedium,
                )
                Text(
                    text = email.createAt,
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.outline,
                )
            }
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surface),
            ) {
                Icon(
                    imageVector = Icons.Default.StarBorder,
                    contentDescription = "Favorite",
                    tint = MaterialTheme.colorScheme.outline,
                )
            }
        }

        Text(
            text = email.subject,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
        )

        Text(
            text = email.body,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.inverseOnSurface),
            ) {
                Text(
                    text = stringResource(id = R.string.reply),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.inverseOnSurface),
            ) {
                Text(
                    text = stringResource(id = R.string.reply_all),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
fun ReplyProfileImage(
    drawableResource: Int,
    description: String,
    modifier: Modifier = Modifier,
) = Image(
    modifier = modifier
        .size(40.dp)
        .clip(CircleShape),
    painter = painterResource(id = drawableResource),
    contentDescription = description,
)

@Composable
fun ReplySearchBar(modifier: Modifier = Modifier) = Row(
    modifier = modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
        .background(MaterialTheme.colorScheme.surface, CircleShape),
    verticalAlignment = Alignment.CenterVertically,
) {
    Icon(
        imageVector = Icons.Default.Search,
        contentDescription = stringResource(id = R.string.search),
        modifier = Modifier.padding(start = 16.dp),
        tint = MaterialTheme.colorScheme.outline,
    )
    Text(
        text = stringResource(id = R.string.search_replies),
        modifier = Modifier
            .weight(1f)
            .padding(16.dp),
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.outline,
    )
    ReplyProfileImage(
        drawableResource = R.drawable.avatar_6,
        description = stringResource(id = R.string.profile),
        modifier = Modifier
            .padding(12.dp)
            .size(32.dp),
    )
}
