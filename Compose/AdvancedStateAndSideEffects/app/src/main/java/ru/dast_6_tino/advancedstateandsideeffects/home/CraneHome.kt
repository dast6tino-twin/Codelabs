package ru.dast_6_tino.advancedstateandsideeffects.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import ru.dast_6_tino.advancedstateandsideeffects.base.*
import ru.dast_6_tino.advancedstateandsideeffects.data.ExploreModel

typealias OnExploreItemClicked = (ExploreModel) -> Unit

enum class CraneScreen {
    Fly, Sleep, Eat
}

@Composable
fun CraneHome(
    onExploreItemClicked: OnExploreItemClicked,
    modifier: Modifier = Modifier,
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.statusBarsPadding(),
        drawerContent = { CraneDrawer() },
    ) { padding ->
        val scope = rememberCoroutineScope()
        CraneHomeContent(
            modifier = modifier.padding(padding),
            onExploreItemClicked = onExploreItemClicked,
            openDrawer = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            },
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CraneHomeContent(
    onExploreItemClicked: OnExploreItemClicked,
    openDrawer: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel(),
) {
    var tabSelected by remember { mutableStateOf(CraneScreen.Fly) }
    val suggestedDestinations by viewModel.suggestedDestinationsFlow
        .collectAsStateWithLifecycle(emptyList(), LocalLifecycleOwner.current)

    BackdropScaffold(
        modifier = modifier,
        scaffoldState = rememberBackdropScaffoldState(BackdropValue.Revealed),
        frontLayerScrimColor = Color.Unspecified,
        appBar = {
            HomeTabBar(openDrawer, tabSelected, onTabSelected = { tabSelected = it })
        },
        backLayerContent = {
            SearchContent(
                tabSelected = tabSelected,
                viewModel = viewModel,
                onPeopleChanged = viewModel::updatePeople,
            )
        },
        frontLayerContent = {
            when (tabSelected) {
                CraneScreen.Fly -> ExploreSection(
                    title = "Explore Flights by Destination",
                    exploreList = suggestedDestinations,
                    onItemClicked = onExploreItemClicked,
                )

                CraneScreen.Sleep -> ExploreSection(
                    title = "Explore Properties by Destination",
                    exploreList = viewModel.hotels,
                    onItemClicked = onExploreItemClicked,
                )

                CraneScreen.Eat -> ExploreSection(
                    title = "Explore Restaurants by Destination",
                    exploreList = viewModel.restaurants,
                    onItemClicked = onExploreItemClicked,
                )
            }
        },
    )
}

@Composable
private fun HomeTabBar(
    openDrawer: () -> Unit,
    tabSelected: CraneScreen,
    onTabSelected: (CraneScreen) -> Unit,
    modifier: Modifier = Modifier,
) = CraneTabBar(
    modifier = modifier,
    onMenuClicked = openDrawer,
) { tabBarModifier ->
    CraneTabs(
        modifier = tabBarModifier,
        titles = CraneScreen.entries.map { it.name },
        tabSelected = tabSelected,
        onTabSelected = { newTab -> onTabSelected(CraneScreen.entries[newTab.ordinal]) },
    )
}

@Composable
private fun SearchContent(
    tabSelected: CraneScreen,
    viewModel: MainViewModel,
    onPeopleChanged: (Int) -> Unit,
) = when (tabSelected) {
    CraneScreen.Fly -> FlySearchContent(
        onPeopleChanged = onPeopleChanged,
        onToDestinationChanged = { viewModel.toDestinationChanged(it) },
    )

    CraneScreen.Sleep -> SleepSearchContent(
        onPeopleChanged = onPeopleChanged,
    )

    CraneScreen.Eat -> EatSearchContent(
        onPeopleChanged = onPeopleChanged,
    )
}
