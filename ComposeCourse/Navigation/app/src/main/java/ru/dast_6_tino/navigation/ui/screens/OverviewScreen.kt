package ru.dast_6_tino.navigation.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.navigation.R
import ru.dast_6_tino.navigation.data.UserData
import ru.dast_6_tino.navigation.ui.components.*
import java.util.Locale

@Composable
fun OverviewScreen(
    onClickSeeAllAccounts: () -> Unit = {},
    onClickSeeAllBills: () -> Unit = {},
    onAccountClick: (String) -> Unit = {},
) = Column(
    modifier = Modifier
        .padding(16.dp)
        .verticalScroll(rememberScrollState())
        .semantics { contentDescription = "Overview Screen" },
) {
    AlertCard()
    Spacer(Modifier.height(RallyDefaultPadding))
    AccountsCard(
        onClickSeeAll = onClickSeeAllAccounts,
        onAccountClick = onAccountClick,
    )
    Spacer(Modifier.height(RallyDefaultPadding))
    BillsCard(
        onClickSeeAll = onClickSeeAllBills,
    )
}

/**
 * The Alerts card within the Rally Overview screen.
 */
@Composable
private fun AlertCard() {
    var showDialog by remember { mutableStateOf(false) }
    val alertMessage = "Heads up, you've used up 90% of your Shopping budget for this month."

    if (showDialog) {
        RallyAlertDialog(
            onDismiss = {
                showDialog = false
            },
            bodyText = alertMessage,
            buttonText = "Dismiss".uppercase(Locale.getDefault()),
        )
    }
    Card {
        Column {
            AlertHeader {
                showDialog = true
            }
            RallyDivider(
                modifier = Modifier.padding(start = RallyDefaultPadding, end = RallyDefaultPadding),
            )
            AlertItem(alertMessage)
        }
    }
}

@Composable
private fun AlertHeader(onClickSeeAll: () -> Unit) = Row(
    modifier = Modifier
        .padding(RallyDefaultPadding)
        .fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween,
) {
    Text(
        text = "Alerts",
        style = MaterialTheme.typography.subtitle2,
        modifier = Modifier.align(Alignment.CenterVertically),
    )
    TextButton(
        onClick = onClickSeeAll,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier.align(Alignment.CenterVertically),
    ) {
        Text(
            text = "SEE ALL",
            style = MaterialTheme.typography.button,
        )
    }
}

@Composable
private fun AlertItem(message: String) = Row(
    modifier = Modifier
        .padding(RallyDefaultPadding)
        // Regard the whole row as one semantics node. This way each row will receive focus as
        // a whole and the focus bounds will be around the whole row content. The semantics
        // properties of the descendants will be merged. If we'd use clearAndSetSemantics instead,
        // we'd have to define the semantics properties explicitly.
        .semantics(mergeDescendants = true) {},
    horizontalArrangement = Arrangement.SpaceBetween,
) {
    Text(
        style = MaterialTheme.typography.body2,
        modifier = Modifier.weight(1f),
        text = message,
    )
    IconButton(
        onClick = {},
        modifier = Modifier
            .align(Alignment.Top)
            .clearAndSetSemantics {},
    ) {
        Icon(Icons.AutoMirrored.Filled.Sort, contentDescription = null)
    }
}

/**
 * Base structure for cards in the Overview screen.
 */
@Composable
private fun <T> OverviewScreenCard(
    title: String,
    amount: Float,
    onClickSeeAll: () -> Unit,
    values: (T) -> Float,
    colors: (T) -> Color,
    data: List<T>,
    row: @Composable (T) -> Unit,
) = Card {
    Column {
        Column(Modifier.padding(RallyDefaultPadding)) {
            Text(text = title, style = MaterialTheme.typography.subtitle2)
            val amountText = "$" + formatAmount(amount)
            Text(text = amountText, style = MaterialTheme.typography.h2)
        }
        OverViewDivider(data, values, colors)
        Column(Modifier.padding(start = 16.dp, top = 4.dp, end = 8.dp)) {
            data.take(SHOWN_ITEMS).forEach { row(it) }
            SeeAllButton(
                modifier = Modifier.clearAndSetSemantics {
                    contentDescription = "All $title"
                },
                onClick = onClickSeeAll,
            )
        }
    }
}

@Composable
private fun <T> OverViewDivider(
    data: List<T>,
    values: (T) -> Float,
    colors: (T) -> Color,
) = Row(Modifier.fillMaxWidth()) {
    data.forEach { item: T ->
        Spacer(
            modifier = Modifier
                .weight(values(item))
                .height(1.dp)
                .background(colors(item)),
        )
    }
}

/**
 * The Accounts card within the Rally Overview screen.
 */
@Composable
private fun AccountsCard(onClickSeeAll: () -> Unit, onAccountClick: (String) -> Unit) {
    val amount = UserData.accounts.map { account -> account.balance }.sum()
    OverviewScreenCard(
        title = stringResource(R.string.accounts),
        amount = amount,
        onClickSeeAll = onClickSeeAll,
        data = UserData.accounts,
        colors = { it.color },
        values = { it.balance },
    ) { account ->
        AccountRow(
            modifier = Modifier.clickable { onAccountClick(account.name) },
            name = account.name,
            number = account.number,
            amount = account.balance,
            color = account.color,
        )
    }
}

/**
 * The Bills card within the Rally Overview screen.
 */
@Composable
private fun BillsCard(onClickSeeAll: () -> Unit) {
    val amount = UserData.bills.map { bill -> bill.amount }.sum()
    OverviewScreenCard(
        title = stringResource(R.string.bills),
        amount = amount,
        onClickSeeAll = onClickSeeAll,
        data = UserData.bills,
        colors = { it.color },
        values = { it.amount },
    ) { bill ->
        BillRow(
            name = bill.name,
            due = bill.due,
            amount = bill.amount,
            color = bill.color,
        )
    }
}

@Composable
private fun SeeAllButton(modifier: Modifier = Modifier, onClick: () -> Unit) = TextButton(
    onClick = onClick,
    modifier = modifier
        .height(44.dp)
        .fillMaxWidth(),
) {
    Text(stringResource(R.string.see_all))
}

private val RallyDefaultPadding = 12.dp

private const val SHOWN_ITEMS = 3
