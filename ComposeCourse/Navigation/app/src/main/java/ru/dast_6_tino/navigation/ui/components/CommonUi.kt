package ru.dast_6_tino.navigation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.navigation.R
import java.text.DecimalFormat

/**
 * A row representing the basic information of an Account.
 */
@Composable
fun AccountRow(
    modifier: Modifier = Modifier,
    name: String,
    number: Int,
    amount: Float,
    color: Color,
) = BaseRow(
    modifier = modifier,
    color = color,
    title = name,
    subtitle = stringResource(R.string.account_redacted) + AccountDecimalFormat.format(number),
    amount = amount,
    negative = false,
)

/**
 * A row representing the basic information of a Bill.
 */
@Composable
fun BillRow(name: String, due: String, amount: Float, color: Color) = BaseRow(
    color = color,
    title = name,
    subtitle = "Due $due",
    amount = amount,
    negative = true,
)

@Composable
private fun BaseRow(
    modifier: Modifier = Modifier,
    color: Color,
    title: String,
    subtitle: String,
    amount: Float,
    negative: Boolean,
) {
    val dollarSign = if (negative) "–$ " else "$ "
    val formattedAmount = formatAmount(amount)
    val description = "$title account ending in ${subtitle.takeLast(4)}, " +
        "current balance $dollarSign$formattedAmount"

    Row(
        modifier = modifier
            .height(68.dp)
            .clearAndSetSemantics {
                contentDescription = description
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val typography = MaterialTheme.typography
        AccountIndicator(color = color, modifier = Modifier)
        Spacer(Modifier.width(12.dp))
        Column(Modifier) {
            Text(text = title, style = typography.body1)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = subtitle, style = typography.subtitle1)
            }
        }
        Spacer(Modifier.weight(1f))
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = dollarSign,
                style = typography.h6,
                modifier = Modifier.align(Alignment.CenterVertically),
            )
            Text(
                text = formattedAmount,
                style = typography.h6,
                modifier = Modifier.align(Alignment.CenterVertically),
            )
        }
        Spacer(Modifier.width(16.dp))

        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Icon(
                imageVector = Icons.Filled.ChevronRight,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(24.dp),
            )
        }
    }
    RallyDivider()
}

/**
 * A vertical colored line that is used in a [BaseRow] to differentiate accounts.
 */
@Composable
private fun AccountIndicator(color: Color, modifier: Modifier = Modifier) = Spacer(
    modifier = modifier
        .size(4.dp, 36.dp)
        .background(color = color),
)

@Composable
fun RallyDivider(modifier: Modifier = Modifier) = Divider(
    color = MaterialTheme.colors.background,
    thickness = 1.dp,
    modifier = modifier,
)

fun formatAmount(amount: Float): String = AmountDecimalFormat.format(amount)

private val AccountDecimalFormat = DecimalFormat("####")
private val AmountDecimalFormat = DecimalFormat("#,###.##")

/**
 * Used with accounts and bills to create the animated circle.
 */
fun <E> List<E>.extractProportions(selector: (E) -> Float): List<Float> {
    val total = sumOf { selector(it).toDouble() }
    return map { (selector(it) / total).toFloat() }
}
