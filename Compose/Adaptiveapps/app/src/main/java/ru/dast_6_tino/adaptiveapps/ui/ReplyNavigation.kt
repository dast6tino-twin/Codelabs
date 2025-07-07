package ru.dast_6_tino.adaptiveapps.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Article
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.People
import androidx.compose.ui.graphics.vector.ImageVector
import ru.dast_6_tino.adaptiveapps.R

/** Navigation destinations in the app. */
enum class ReplyDestination(
    @StringRes val labelRes: Int,
    val icon: ImageVector,
) {

    Inbox(R.string.tab_inbox, Icons.Default.Inbox),

    Articles(R.string.tab_article, Icons.AutoMirrored.Filled.Article),

    Messages(R.string.tab_dm, Icons.AutoMirrored.Outlined.Chat),

    Groups(R.string.tab_groups, Icons.Outlined.People),

}
