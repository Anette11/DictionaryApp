package com.example.dictionaryapp.ui.components.history_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionaryapp.repository.SearchHistoryRepository
import com.example.dictionaryapp.ui.components.history_screen.utils.HistoryItem
import com.example.dictionaryapp.utils.formatDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository
) : ViewModel() {

    private val _searchHistory = mutableStateOf<List<HistoryItem>>(emptyList())
    val searchHistory: State<List<HistoryItem>> = _searchHistory

    private val _clearHistoryDialog = mutableStateOf(false)
    val clearHistoryDialog: State<Boolean> = _clearHistoryDialog

    fun updateValueClearHistoryDialog(
        boolean: Boolean
    ) = viewModelScope.launch(Dispatchers.IO) {
        _clearHistoryDialog.value = boolean
    }

    init {
        getHistory()
    }

    private fun getHistory() = viewModelScope.launch(Dispatchers.IO) {
        searchHistoryRepository.getAllSearchHistory()
            .onEach { searchHistoryList ->
                val converted = mutableListOf<HistoryItem>()
                searchHistoryList.groupBy { searchHistory ->
                    searchHistory.date
                }.forEach { (key, value) ->
                    converted.add(HistoryItem.Date(date = key.formatDate()))
                    value.forEach {
                        converted.add(HistoryItem.Item(value = it.value))
                    }
                }
                withContext(Dispatchers.Main) {
                    _searchHistory.value = converted.distinct()
                }
            }.launchIn(viewModelScope)
    }

    fun clearAllSearchHistory() = viewModelScope.launch(Dispatchers.IO) {
        searchHistoryRepository.clearAllSearchHistory()
    }
}