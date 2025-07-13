package ru.dast_6_tino.businesscard.ui.screen.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    val stateFlow: StateFlow<MainViewState> = MutableStateFlow(
        value = MainViewState(DEFAULT_PHONE, DEFAULT_SHARE, DEFAULT_EMAIL),
    )

    private companion object {

        const val DEFAULT_PHONE = "00000000000"
        const val DEFAULT_SHARE = "@SocialMediaHandle"
        const val DEFAULT_EMAIL = "email@domain.com"

    }

}
