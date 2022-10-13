package com.example.dictionaryapp.navigation

import androidx.annotation.DrawableRes
import com.example.dictionaryapp.R

sealed class BottomNavigationItem(
    val route: String,
    val title: String,
    @DrawableRes val iconId: Int
) {
    object Home : BottomNavigationItem(
        route = "home",
        title = "Home",
        iconId = R.drawable.ic_home
    ) {
        fun getArg() = "value"
    }

    object History : BottomNavigationItem(
        route = "history",
        title = "History",
        iconId = R.drawable.ic_history
    )
}