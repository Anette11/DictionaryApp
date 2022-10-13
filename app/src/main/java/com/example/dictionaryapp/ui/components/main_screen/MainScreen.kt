package com.example.dictionaryapp.ui.components.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dictionaryapp.navigation.components.WordBottomNavigation
import com.example.dictionaryapp.navigation.components.WordNavHost

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController()
) = Scaffold(
    modifier = Modifier.fillMaxSize(),
    bottomBar = {
        WordBottomNavigation(navController = navController)
    }
) {
    Column(
        modifier = Modifier.padding(it)
    ) {
        WordNavHost(navController = navController)
    }
}