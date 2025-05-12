package ru.dast_6_tino.theming.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.theming.R
import ru.dast_6_tino.theming.data.Account
import ru.dast_6_tino.theming.data.Email
import ru.dast_6_tino.theming.data.MailboxType
import ru.dast_6_tino.theming.ui.components.EmailDetailAppBar
import ru.dast_6_tino.theming.ui.components.ReplyEmailListItem
import ru.dast_6_tino.theming.ui.components.ReplyEmailThreadItem
import ru.dast_6_tino.theming.ui.components.ReplySearchBar
import ru.dast_6_tino.theming.ui.theme.ReplyTheme

@Composable
fun ReplyInboxScreen(
    replyHomeUIState: ReplyHomeUIState,
    closeDetailScreen: () -> Unit,
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val emailLazyListState = rememberLazyListState()

    Box(modifier = modifier.fillMaxSize()) {
        ReplyEmailListContent(
            replyHomeUIState = replyHomeUIState,
            emailLazyListState = emailLazyListState,
            modifier = Modifier.fillMaxSize(),
            closeDetailScreen = closeDetailScreen,
            navigateToDetail = navigateToDetail,
        )

        LargeFloatingActionButton(
            onClick = { /*Click Implementation*/ },
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = stringResource(id = R.string.edit),
                modifier = Modifier.size(28.dp),
            )
        }
    }
}

@Composable
fun ReplyEmailListContent(
    replyHomeUIState: ReplyHomeUIState,
    emailLazyListState: LazyListState,
    closeDetailScreen: () -> Unit,
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (replyHomeUIState.selectedEmail != null && replyHomeUIState.isDetailOnlyOpen) {
        BackHandler {
            closeDetailScreen()
        }
        ReplyEmailDetail(email = replyHomeUIState.selectedEmail) {
            closeDetailScreen()
        }
    } else {
        ReplyEmailList(
            emails = replyHomeUIState.emails,
            emailLazyListState = emailLazyListState,
            modifier = modifier,
            navigateToDetail = navigateToDetail,
        )
    }
}

@Composable
fun ReplyEmailList(
    emails: List<Email>,
    emailLazyListState: LazyListState,
    modifier: Modifier = Modifier,
    selectedEmail: Email? = null,
    navigateToDetail: (Long) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        state = emailLazyListState,
    ) {
        item {
            ReplySearchBar(modifier = Modifier.fillMaxWidth())
        }
        items(items = emails, key = { it.id }) { email ->
            ReplyEmailListItem(
                email = email,
                isSelected = email.id == selectedEmail?.id,
                navigateToDetail = { emailId ->
                    navigateToDetail(emailId)
                },
            )
        }
    }
}

@Composable
fun ReplyEmailDetail(
    email: Email,
    modifier: Modifier = Modifier,
    isFullScreen: Boolean = true,
    onBackPressed: () -> Unit = {},
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp),
    ) {
        item {
            EmailDetailAppBar(email, isFullScreen, onBackPressed = { onBackPressed() })
        }
        items(items = email.threads, key = { it.id }) { email ->
            ReplyEmailThreadItem(email = email)
        }
    }
}

@DarkLightPreviews
@Composable
private fun ReplyInboxScreenPreview() {
    val state = ReplyHomeUIState()
    ReplyTheme {
        ReplyInboxScreen(state, closeDetailScreen = {}, navigateToDetail = {})
    }
}

@DarkLightPreviews
@Composable
private fun ReplyEmailListContentPreview() {
    val state = ReplyHomeUIState()
    val emailLazyListState = rememberLazyListState()
    ReplyTheme {
        Surface {
            ReplyEmailListContent(state, emailLazyListState, closeDetailScreen = {}, navigateToDetail = {})
        }
    }
}

@DarkLightPreviews
@Composable
private fun ReplyEmailDetailPreview() {
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
        isStarred = true,
        mailbox = MailboxType.SPAM,
        createdAt = "createdAt",
        threads = emptyList(),
    )
    ReplyTheme {
        Surface {
            ReplyEmailDetail(email)
        }
    }
}