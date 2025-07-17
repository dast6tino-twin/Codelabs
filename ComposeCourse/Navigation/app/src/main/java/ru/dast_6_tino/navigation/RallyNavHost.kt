package ru.dast_6_tino.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.dast_6_tino.navigation.ui.screens.*

@Composable
fun RallyNavHost(navController: NavHostController, modifier: Modifier = Modifier) = NavHost(
    navController = navController,
    startDestination = Destinations.Overview.route,
    modifier = modifier,
) {
    composable(route = Destinations.Overview.route) {
        OverviewScreen(
            onClickSeeAllAccounts = {
                navController.navigateSingleTopTo(Destinations.Accounts.route)
            },
            onClickSeeAllBills = {
                navController.navigateSingleTopTo(Destinations.Bills.route)
            },
            onAccountClick = navController::navigateToSingleAccount,
        )
    }
    composable(route = Destinations.Accounts.route) {
        AccountsScreen(
            onAccountClick = navController::navigateToSingleAccount,
        )
    }
    composable(
        route = Destinations.SingleAccount.routeWithArgs,
        arguments = Destinations.SingleAccount.arguments,
        deepLinks = Destinations.SingleAccount.deepLinks,
    ) { entry ->
        // Retrieve the passed argument
        val accountType = Destinations.SingleAccount.extractAccountType(entry.arguments)
        // Pass accountType to SingleAccountScreen
        SingleAccountScreen(accountType)
    }
    composable(route = Destinations.Bills.route) {
        BillsScreen()
    }
}

fun NavHostController.navigateSingleTopTo(route: String) = navigate(route = route) {
    popUpTo(graph.startDestinationId) { saveState = true }
    launchSingleTop = true
    restoreState = true
}

private fun NavHostController.navigateToSingleAccount(type: String) = navigateSingleTopTo(
    route = "${Destinations.SingleAccount.route}/$type",
)
