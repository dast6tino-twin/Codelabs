package ru.dast_6_tino.advancedstateandsideeffects.home

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.dast_6_tino.advancedstateandsideeffects.R
import ru.dast_6_tino.advancedstateandsideeffects.base.SimpleUserInput

@Composable
fun FlySearchContent(
    onPeopleChanged: (Int) -> Unit,
    onToDestinationChanged: (String) -> Unit,
) = CraneSearch {
    PeopleUserInput(
        titleSuffix = ", Economy",
        onPeopleChanged = onPeopleChanged,
    )
    Spacer(Modifier.height(8.dp))
    FromDestination()
    Spacer(Modifier.height(8.dp))
    ToDestinationUserInput(onToDestinationChanged = onToDestinationChanged)
    Spacer(Modifier.height(8.dp))
    DatesUserInput()
}

@Composable
fun SleepSearchContent(onPeopleChanged: (Int) -> Unit) = CraneSearch {
    PeopleUserInput(onPeopleChanged = onPeopleChanged)
    Spacer(Modifier.height(8.dp))
    DatesUserInput()
    Spacer(Modifier.height(8.dp))
    SimpleUserInput(caption = "Select Location", vectorImageId = R.drawable.ic_hotel)
}

@Composable
fun EatSearchContent(onPeopleChanged: (Int) -> Unit) = CraneSearch {
    PeopleUserInput(onPeopleChanged = onPeopleChanged)
    Spacer(Modifier.height(8.dp))
    DatesUserInput()
    Spacer(Modifier.height(8.dp))
    SimpleUserInput(caption = "Select Time", vectorImageId = R.drawable.ic_time)
    Spacer(Modifier.height(8.dp))
    SimpleUserInput(caption = "Select Location", vectorImageId = R.drawable.ic_restaurant)
}

@Composable
private fun CraneSearch(content: @Composable () -> Unit) = Column(
    modifier = Modifier.padding(start = 24.dp, top = 0.dp, end = 24.dp, bottom = 12.dp),
) {
    content.invoke()
}
