package ru.dast_6_tino.theming.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.theming.R
import ru.dast_6_tino.theming.data.Account
import ru.dast_6_tino.theming.data.Email
import ru.dast_6_tino.theming.data.MailboxType
import ru.dast_6_tino.theming.ui.DarkLightPreviews
import ru.dast_6_tino.theming.ui.theme.ReplyTheme

@Composable
fun ReplyEmailListItem(
    email: Email,
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
) {
    val cardColor = if (email.isImportant) {
        MaterialTheme.colorScheme.secondaryContainer
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .semantics { selected = isSelected }
            .clickable { navigateToDetail(email.id) },
        colors = CardDefaults.cardColors().copy(containerColor = cardColor),
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
                        text = email.createdAt,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                IconButton(
                    onClick = { /*Click Implementation*/ },
                    modifier = Modifier
                        .clip(CircleShape),
                ) {
                    Icon(
                        imageVector = Icons.Default.StarBorder,
                        contentDescription = stringResource(id = R.string.description_favorite),
                    )
                }
            }

            Text(
                text = email.subject,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
            )
            Text(
                text = email.body,
                maxLines = 2,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@DarkLightPreviews
@Composable
private fun ReplyEmailListItemPreview() {
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
            ReplyEmailListItem(email, navigateToDetail = {})
        }
    }
}

@DarkLightPreviews
@Composable
private fun ReplyEmailListItemImportantPreview() {
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
        isImportant = true,
        isStarred = false,
        mailbox = MailboxType.SPAM,
        createdAt = "createdAt",
        threads = emptyList(),
    )
    ReplyTheme {
        Surface {
            ReplyEmailListItem(email, navigateToDetail = {})
        }
    }
}
