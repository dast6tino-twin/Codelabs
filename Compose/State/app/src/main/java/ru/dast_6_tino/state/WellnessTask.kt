package ru.dast_6_tino.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class WellnessTask(
    val id: Int,
    val label: String,
    isCheckedInitial: Boolean = false,
) {

    // The lesson uses this approach,
    // but I'm not sure it's a good solution.
    // For simplicity and to follow the lesson,
    // I will leave it like this.
    var isCheckedState by mutableStateOf(isCheckedInitial)

}
