package ru.dast_6_tino.adaptiveapps.data

import ru.dast_6_tino.adaptiveapps.data.local.LocalAccountsDataProvider

/**
 * A simple data class to represent an Email.
 */
data class Email(
    val id: Long,
    val sender: Account,
    val recipients: List<Account> = emptyList(),
    val subject: String = "",
    val body: String = "",
    val attachments: List<EmailAttachment> = emptyList(),
    var isImportant: Boolean = false,
    var isStarred: Boolean = false,
    var mailbox: MailboxType = MailboxType.INBOX,
    var createAt: String,
    val replies: List<Email> = emptyList(),
) {

    val senderPreview: String = "${sender.fullName} - 4 hrs ago"
    val hasBody: Boolean = body.isNotBlank()
    val hasAttachments: Boolean = attachments.isNotEmpty()
    val recipientsPreview: String = recipients
        .map { it.firstName }
        .fold("") { name, acc -> "$acc, $name" }
    val nonUserAccountRecipients: List<Account> = recipients
        .filterNot { LocalAccountsDataProvider.isUserAccount(it.uid) }

}
