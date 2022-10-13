package com.example.dictionaryapp.navigation

import androidx.navigation.NavHostController

fun NavHostController.navigateTo(
    route: String
) = this.navigate(route) {
    this@navigateTo.graph.startDestinationRoute?.let {
        popUpTo(route = it) {
            saveState = true
        }
    }
    launchSingleTop = true
    restoreState = true
}

fun NavHostController.navigateToClearAll(
    route: String
) = this.navigate(route) {
    popUpTo(0) {
        saveState = true
        inclusive = true
    }
    launchSingleTop = true
    restoreState = true
}