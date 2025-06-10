package ru.dast_6_tino.advancedstateandsideeffects.home

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.filter
import ru.dast_6_tino.advancedstateandsideeffects.R
import ru.dast_6_tino.advancedstateandsideeffects.base.CraneEditableUserInput
import ru.dast_6_tino.advancedstateandsideeffects.base.CraneUserInput
import ru.dast_6_tino.advancedstateandsideeffects.base.rememberEditableUserInputState
import ru.dast_6_tino.advancedstateandsideeffects.home.PeopleUserInputAnimationState.Invalid
import ru.dast_6_tino.advancedstateandsideeffects.home.PeopleUserInputAnimationState.Valid
import ru.dast_6_tino.advancedstateandsideeffects.ui.CraneTheme
import ru.dast_6_tino.advancedstateandsideeffects.ui.DarkLightPreviews

enum class PeopleUserInputAnimationState { Valid, Invalid }

class PeopleUserInputState {

    var people by mutableIntStateOf(1)
        private set

    val animationState: MutableTransitionState<PeopleUserInputAnimationState> = MutableTransitionState(Valid)

    fun addPerson() {
        people = (people % (MAX_PEOPLE + 1)) + 1
        updateAnimationState()
    }

    private fun updateAnimationState() {
        val newState = if (people > MAX_PEOPLE) Invalid else Valid

        if (animationState.currentState != newState) animationState.targetState = newState
    }

}

@Composable
fun PeopleUserInput(
    titleSuffix: String? = "",
    onPeopleChanged: (Int) -> Unit,
    peopleState: PeopleUserInputState = remember { PeopleUserInputState() },
) = Column {
    val transitionState = remember { peopleState.animationState }
    val tint = tintPeopleUserInput(transitionState)

    val people = peopleState.people
    CraneUserInput(
        text = if (people == 1) "$people Adult$titleSuffix" else "$people Adults$titleSuffix",
        vectorImageId = R.drawable.ic_person,
        tint = tint.value,
        onClick = {
            peopleState.addPerson()
            onPeopleChanged(peopleState.people)
        },
    )
    if (transitionState.targetState == Invalid) {
        Text(
            text = "Error: We don't support more than $MAX_PEOPLE people",
            style = MaterialTheme.typography.body1.copy(color = MaterialTheme.colors.secondary),
        )
    }
}

@Composable
fun FromDestination() = CraneUserInput(text = "Seoul, South Korea", vectorImageId = R.drawable.ic_location)

@Composable
fun ToDestinationUserInput(onToDestinationChanged: (String) -> Unit) {
    val editableUserInputState = rememberEditableUserInputState("Choose Destination")
    CraneEditableUserInput(
        state = editableUserInputState,
        caption = "To",
        vectorImageId = R.drawable.ic_plane,
    )

    val currentOnDestinationChanged by rememberUpdatedState(onToDestinationChanged)
    LaunchedEffect(editableUserInputState) {
        snapshotFlow { editableUserInputState.text }
            .filter { !editableUserInputState.isHint }
            .collect { currentOnDestinationChanged.invoke(it) }
    }
}

@Composable
fun DatesUserInput() = CraneUserInput(
    caption = "Select Dates",
    text = "",
    vectorImageId = R.drawable.ic_calendar,
)

@Composable
private fun tintPeopleUserInput(
    transitionState: MutableTransitionState<PeopleUserInputAnimationState>,
): State<Color> {
    val validColor = MaterialTheme.colors.onSurface
    val invalidColor = MaterialTheme.colors.secondary

    return rememberTransition(transitionState, label = "")
        .animateColor(
            transitionSpec = { tween(durationMillis = 300) },
            label = "",
        ) {
            if (it == Valid) validColor else invalidColor
        }
}

@DarkLightPreviews
@Composable
fun PeopleUserInputPreview() = CraneTheme {
    PeopleUserInput(onPeopleChanged = {})
}
