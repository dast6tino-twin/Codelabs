package ru.dast_6_tino.navigation.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import ru.dast_6_tino.navigation.data.UserData
import ru.dast_6_tino.navigation.ui.components.AccountRow
import ru.dast_6_tino.navigation.ui.components.StatementBody

/**
 * Detail screen for a single account.
 */
@Composable
fun SingleAccountScreen(accountType: String?) {
    val account = remember(accountType) { UserData.getAccount(accountType) }
    StatementBody(
        items = listOf(account),
        colors = { account.color },
        amounts = { account.balance },
        amountsTotal = account.balance,
        circleLabel = account.name,
        modifier = Modifier.semantics { contentDescription = "Single account Screen" },
    ) { row ->
        AccountRow(
            name = row.name,
            number = row.number,
            amount = row.balance,
            color = row.color,
        )
    }
}
