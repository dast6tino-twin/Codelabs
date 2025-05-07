package ru.dast_6_tino.basiclayouts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class BasicLayoutsAppViewModel : ViewModel() {

    private var _screen by mutableStateOf(BasicLayoutsAppScreen.SPA)
    val screen: BasicLayoutsAppScreen get() = _screen

    fun onScreenSelected(screen: BasicLayoutsAppScreen) {
        _screen = screen
    }

}
