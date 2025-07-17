package ru.dast_6_tino.testing.ui.accounts

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.dast_6_tino.testing.ui.components.AccountRow
import ru.dast_6_tino.testing.ui.components.StatementBody
import ru.dast_6_tino.testing.R
import ru.dast_6_tino.testing.data.Account

/**
 * The Accounts screen.
 */
@Composable
fun AccountsBody(accounts: List<Account>) = StatementBody(
    items = accounts,
    amounts = { account -> account.balance },
    colors = { account -> account.color },
    amountsTotal = accounts.map { account -> account.balance }.sum(),
    circleLabel = stringResource(R.string.total),
    rows = { account ->
        AccountRow(
            name = account.name,
            number = account.number,
            amount = account.balance,
            color = account.color,
        )
    },
)
