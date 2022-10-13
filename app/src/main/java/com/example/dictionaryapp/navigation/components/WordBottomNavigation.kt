package com.example.dictionaryapp.navigation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.dictionaryapp.R
import com.example.dictionaryapp.navigation.BottomNavigationItem
import com.example.dictionaryapp.navigation.navigateTo

@Composable
fun WordBottomNavigation(
    navController: NavHostController
) {
    val items = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.History
    )
    BottomNavigation(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = colorResource(id = R.color.violet),
        contentColor = Color.White,
        elevation = AppBarDefaults.BottomAppBarElevation
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute?.contains(item.route) ?: false,
                onClick = { navController.navigateTo(route = item.route) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
                        contentDescription = null
                    )
                },
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(alpha = 0.5f),
                alwaysShowLabel = true
            )
        }
    }
}