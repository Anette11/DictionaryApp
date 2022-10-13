package com.example.dictionaryapp.ui.components.history_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dictionaryapp.R
import com.example.dictionaryapp.ui.components.WordDivider
import com.example.dictionaryapp.ui.components.history_screen.utils.HistoryItem

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel(),
    navigateToHomeScreen: () -> Unit,
    navigateToHomeScreenWithArg: (String) -> Unit
) {
    val searchHistory by viewModel.searchHistory
    val clearHistoryDialog by viewModel.clearHistoryDialog

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            HistoryTopAppBar(
                onSearchClick = navigateToHomeScreen,
                onClearHistoryClick = {
                    viewModel.updateValueClearHistoryDialog(true)
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = dimensionResource(id = R.dimen._8dp))
            ) {
                items(searchHistory.size) { index ->
                    val item = searchHistory[index]
                    if (item is HistoryItem.Date) {
                        HistoryDate(text = item.date)
                    } else if (item is HistoryItem.Item) {
                        HistoryItem(
                            text = item.value,
                            onClick = { navigateToHomeScreenWithArg(item.value) }
                        )
                        if (index + 1 != searchHistory.size) {
                            WordDivider()
                        }
                    }
                }
            }
            if (clearHistoryDialog) {
                HistoryClearDialog(
                    onConfirmClick = {
                        viewModel.clearAllSearchHistory()
                        viewModel.updateValueClearHistoryDialog(false)
                    },
                    onDismissClick = {
                        viewModel.updateValueClearHistoryDialog(false)
                    }
                )
            }
        }
    }
}