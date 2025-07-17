package ru.dast_6_tino.navigation.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import ru.dast_6_tino.navigation.ui.components.BillRow
import ru.dast_6_tino.navigation.ui.components.StatementBody
import ru.dast_6_tino.navigation.R
import ru.dast_6_tino.navigation.data.Bill
import ru.dast_6_tino.navigation.data.UserData

/**
 * The Bills screen.
 */
@Composable
fun BillsScreen(
    bills: List<Bill> = remember { UserData.bills },
) = StatementBody(
    modifier = Modifier.clearAndSetSemantics { contentDescription = "Bills Screen" },
    items = bills,
    amounts = { bill -> bill.amount },
    colors = { bill -> bill.color },
    amountsTotal = bills.map { bill -> bill.amount }.sum(),
    circleLabel = stringResource(R.string.due),
    rows = { bill ->
        BillRow(bill.name, bill.due, bill.amount, bill.color)
    },
)
