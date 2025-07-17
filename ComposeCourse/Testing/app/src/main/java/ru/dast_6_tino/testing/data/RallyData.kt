package ru.dast_6_tino.testing.data

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/* Hard-coded data for the Rally sample. */

@Immutable
data class Account(
    val name: String,
    val number: Int,
    val balance: Float,
    val color: Color,
)

@Immutable
data class Bill(
    val name: String,
    val due: String,
    val amount: Float,
    val color: Color,
)

object UserData {

    val accounts: List<Account> = listOf(
        Account(
            name = "Checking",
            number = 1234,
            balance = 2215.13f,
            color = Color(0xFF004940),
        ),
        Account(
            name = "Home Savings",
            number = 5678,
            balance = 8676.88f,
            color = Color(0xFF005D57),
        ),
        Account(
            name = "Car Savings",
            number = 9012,
            balance = 987.48f,
            color = Color(0xFF04B97F),
        ),
        Account(
            name = "Vacation",
            number = 3456,
            balance = 253f,
            color = Color(0xFF37EFBA),
        ),
    )
    val bills: List<Bill> = listOf(
        Bill(
            name = "RedPay Credit",
            due = "Jan 29",
            amount = 45.36f,
            color = Color(0xFFFFDC78),
        ),
        Bill(
            name = "Rent",
            due = "Feb 9",
            amount = 1200f,
            color = Color(0xFFFF6951),
        ),
        Bill(
            name = "TabFine Credit",
            due = "Feb 22",
            amount = 87.33f,
            color = Color(0xFFFFD7D0),
        ),
        Bill(
            name = "ABC Loans",
            due = "Feb 29",
            amount = 400f,
            color = Color(0xFFFFAC12),
        ),
        Bill(
            name = "ABC Loans 2",
            due = "Feb 29",
            amount = 77.4f,
            color = Color(0xFFFFAC12),
        ),
    )
}
