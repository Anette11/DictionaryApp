package com.example.dictionaryapp.ui.components.history_screen.utils

sealed class HistoryItem {

    data class Date(
        val date: String
    ) : HistoryItem()

    data class Item(
        val value: String
    ) : HistoryItem()
}