package ru.dast_6_tino.accessibility.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.dast_6_tino.accessibility.data.AppContainer
import ru.dast_6_tino.accessibility.ui.theme.JetnewsTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun JetnewsApp(appContainer: AppContainer) = JetnewsTheme {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = false)
    }

    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: MainDestinations.HOME_ROUTE

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(modifier = Modifier.width(300.dp), windowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp)) {
                AppDrawer(
                    currentRoute = currentRoute,
                    navigateToHome = { navController.navigate(MainDestinations.HOME_ROUTE) },
                    navigateToInterests = { navController.navigate(MainDestinations.INTERESTS_ROUTE) },
                    closeDrawer = { coroutineScope.launch { drawerState.close() } },
                )
            }
        },
        content = {
            Scaffold {
                JetnewsNavGraph(
                    appContainer = appContainer,
                    navController = navController,
                    drawerState = drawerState,
                )
            }
        },
    )
}
