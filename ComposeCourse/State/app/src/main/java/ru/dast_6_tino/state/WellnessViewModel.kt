package ru.dast_6_tino.state

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

class WellnessViewModel : ViewModel() {

    private val _tasks: SnapshotStateList<WellnessTask> = MutableList(100) { index ->
        WellnessTask(index, "Task #$index")
    }.toMutableStateList()
    val task: List<WellnessTask> get() = _tasks

    fun onCheckClicked(id: Int, isChecked: Boolean) = _tasks.find { it.id == id }
        ?.let { task -> task.isCheckedState = isChecked }

    fun removeTask(task: WellnessTask) = _tasks.remove(task)

}
