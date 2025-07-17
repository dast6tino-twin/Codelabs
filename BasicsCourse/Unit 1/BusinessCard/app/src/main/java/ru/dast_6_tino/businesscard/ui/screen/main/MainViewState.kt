package ru.dast_6_tino.businesscard.ui.screen.main

data class MainViewState(
    val phone: String,
    val share: String,
    val email: String,
) {

    val formattedPhone: String
        get() = StringBuilder(phone)
            .insert(0, "+")
            .insert(2, " (")
            .insert(7, ") ")
            .insert(12, "-")
            .insert(15, "-")
            .toString()

}
