package ru.dast_6_tino.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import androidx.savedstate.SavedState

/**
 * Contract for information needed on every Rally navigation destination
 */
sealed interface Destinations {

    val icon: ImageVector
    val route: String

    /**
     * Rally app navigation destinations
     */
    data object Overview : Destinations {

        override val icon = Icons.Filled.PieChart
        override val route = "overview"

    }

    data object Accounts : Destinations {

        override val icon = Icons.Filled.AttachMoney
        override val route = "accounts"

    }

    data object Bills : Destinations {

        override val icon = Icons.Filled.MoneyOff
        override val route = "bills"

    }

    data object SingleAccount : Destinations {

        // Added for simplicity, this icon will not in fact be used, as SingleAccount isn't
        // part of the RallyTabRow selection
        override val icon = Icons.Filled.Money
        override val route = "single_account"

        private const val ACCOUNT_TYPE_ARG = "account_type"
        val routeWithArgs = "$route/{$ACCOUNT_TYPE_ARG}"
        val arguments = listOf(
            navArgument(ACCOUNT_TYPE_ARG) { type = NavType.StringType },
        )

        fun extractAccountType(state: SavedState?): String? = state?.getString(ACCOUNT_TYPE_ARG)

        val deepLinks = listOf(
            navDeepLink { uriPattern = "$DEEPLINK_BASE$route/{$ACCOUNT_TYPE_ARG}"},
        )

    }

    companion object {

        // Screens to be displayed in the top RallyTabRow
        val tabs = listOf(Overview, Accounts, Bills)

        private const val DEEPLINK_BASE = "rally://"

    }

}
