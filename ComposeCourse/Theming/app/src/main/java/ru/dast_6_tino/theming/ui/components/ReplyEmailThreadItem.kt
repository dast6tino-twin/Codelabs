package ru.dast_6_tino.theming.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.theming.R
import ru.dast_6_tino.theming.data.Account
import ru.dast_6_tino.theming.data.Email
import ru.dast_6_tino.theming.data.MailboxType
import ru.dast_6_tino.theming.ui.DarkLightPreviews
import ru.dast_6_tino.theming.ui.theme.ReplyTheme

@Composable
fun ReplyEmailThreadItem(
    email: Email,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background, MaterialTheme.shapes.medium)
            .padding(20.dp),
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            val sender = email.sender
            ReplyProfileImage(
                drawableResource = sender.avatar,
                description = sender.fullName,
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp, vertical = 4.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = sender.firstName,
                    style = MaterialTheme.typography.labelMedium,
                )
                Text(
                    text = stringResource(id = R.string.twenty_mins_ago),
                    style = MaterialTheme.typography.labelMedium,
                )
            }
            IconButton(
                onClick = { /*Click Implementation*/ },
                modifier = Modifier.clip(CircleShape),
            ) {
                val isStarred = email.isStarred
                val tint = if (isStarred) {
                    MaterialTheme.colorScheme.secondary
                } else {
                    MaterialTheme.colorScheme.outline
                }
                Icon(
                    imageVector = if (isStarred) Icons.Default.Star else Icons.Default.StarBorder,
                    contentDescription = stringResource(id = R.string.description_favorite),
                    tint = tint,
                )
            }
        }

        Text(
            text = email.subject,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
        )

        Text(
            text = email.body,
            style = MaterialTheme.typography.bodyLarge,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Button(
                onClick = { /*Click Implementation*/ },
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = stringResource(id = R.string.reply),
                )
            }
            Button(
                onClick = { /*Click Implementation*/ },
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = stringResource(id = R.string.reply_all),
                )
            }
        }
    }
}

@DarkLightPreviews
@Composable
private fun ReplyEmailThreadItemPreview() {
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
            ReplyEmailThreadItem(email)
        }
    }
}

@DarkLightPreviews
@Composable
private fun ReplyEmailThreadItemStarredPreview() {
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
            ReplyEmailThreadItem(email)
        }
    }
}
