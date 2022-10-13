package com.example.dictionaryapp.navigation.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.dictionaryapp.navigation.BottomNavigationItem
import com.example.dictionaryapp.navigation.navigateToClearAll
import com.example.dictionaryapp.ui.components.history_screen.HistoryScreen
import com.example.dictionaryapp.ui.components.home_screen.HomeScreen

@Composable
fun WordNavHost(
    navController: NavHostController,
    startDestination: String = BottomNavigationItem.Home.route
) = NavHost(
    navController = navController,
    startDestination = startDestination
) {
    composable(
        route = BottomNavigationItem.Home.route
    ) {
        HomeScreen()
    }
    composable(
        route = BottomNavigationItem.Home.route.plus("/{${BottomNavigationItem.Home.getArg()}}"),
        arguments = listOf(
            navArgument(BottomNavigationItem.Home.getArg()) {
            }
        )
    ) { navBackStackEntry ->
        HomeScreen(
            value = navBackStackEntry.arguments?.getString(BottomNavigationItem.Home.getArg()) ?: ""
        )
    }
    composable(
        route = BottomNavigationItem.History.route
    ) {
        HistoryScreen(
            navigateToHomeScreen = {
                navController.navigateUp()
            },
            navigateToHomeScreenWithArg = { value ->
                navController.navigateToClearAll(route = BottomNavigationItem.Home.route.plus("/$value"))
            }
        )
    }
}