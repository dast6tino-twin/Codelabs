package ru.dast_6_tino.theming.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.theming.R
import ru.dast_6_tino.theming.data.Account
import ru.dast_6_tino.theming.data.Email
import ru.dast_6_tino.theming.data.MailboxType
import ru.dast_6_tino.theming.ui.DarkLightPreviews
import ru.dast_6_tino.theming.ui.theme.ReplyTheme

@Composable
fun ReplySearchBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background, CircleShape),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = stringResource(id = R.string.search),
            modifier = Modifier.padding(start = 16.dp),
        )
        Text(
            text = stringResource(id = R.string.search_replies),
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            style = MaterialTheme.typography.bodyMedium,
        )
        ReplyProfileImage(
            drawableResource = R.drawable.avatar_6,
            description = stringResource(id = R.string.profile),
            modifier = Modifier
                .padding(12.dp)
                .size(32.dp),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailDetailAppBar(
    email: Email,
    isFullScreen: Boolean,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        modifier = modifier,
        title = {
            val horizontalAlignment = if (isFullScreen) Alignment.CenterHorizontally else Alignment.Start
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = horizontalAlignment,
            ) {
                Text(
                    text = email.subject,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = "${email.threads.size} ${stringResource(id = R.string.messages)}",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.outline,
                )
            }
        },
        navigationIcon = {
            if (isFullScreen) {
                FilledIconButton(
                    onClick = onBackPressed,
                    modifier = Modifier.padding(8.dp),
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface,
                    ),
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button),
                        modifier = Modifier.size(14.dp),
                    )
                }
            }
        },
        actions = {
            IconButton(
                onClick = { /*Click Implementation*/ },
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = stringResource(id = R.string.more_options_button),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        },
    )
}

@DarkLightPreviews
@Composable
private fun ReplySearchBarPreview() {
    ReplyTheme {
        Surface {
            ReplySearchBar()
        }
    }
}

@DarkLightPreviews
@Composable
private fun EmailDetailAppBarPreview() {
    val account = Account(
        id = 0,
        uid = 0,
        firstName = "firstName",
        lastName = "lastName",
        email = "email",
        altEmail = "altEmail",
        avatar = R.drawable.avatar_2,
        isCurrentAccount = false,
    )
    val email = Email(
        id = 0,
        sender = account,
        recipients = emptyList(),
        subject = "subject",
        body = "body",
        attachments = emptyList(),
        isImportant = false,
        isStarred = false,
        mailbox = MailboxType.SPAM,
        createdAt = "createdAt",
        threads = emptyList(),
    )
    ReplyTheme {
        Surface {
            EmailDetailAppBar(email, true, onBackPressed = {})
        }
    }
}
