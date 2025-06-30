package ru.dast_6_tino.testing.ui.bills

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.dast_6_tino.testing.ui.components.BillRow
import ru.dast_6_tino.testing.ui.components.StatementBody
import ru.dast_6_tino.testing.R
import ru.dast_6_tino.testing.data.Bill

/**
 * The Bills screen.
 */
@Composable
fun BillsBody(bills: List<Bill>) = StatementBody(
    items = bills,
    amounts = { bill -> bill.amount },
    colors = { bill -> bill.color },
    amountsTotal = bills.map { bill -> bill.amount }.sum(),
    circleLabel = stringResource(R.string.due),
    rows = { bill ->
        BillRow(bill.name, bill.due, bill.amount, bill.color)
    },
)
