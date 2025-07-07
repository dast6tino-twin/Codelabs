package ru.dast_6_tino.adaptiveapps.ui

import ru.dast_6_tino.adaptiveapps.data.Email

data class ReplyHomeUIState(
    val emails: List<Email> = emptyList(),
    val selectedEmail: Email? = null,
    val loading: Boolean = false,
    val error: String? = null,
)
