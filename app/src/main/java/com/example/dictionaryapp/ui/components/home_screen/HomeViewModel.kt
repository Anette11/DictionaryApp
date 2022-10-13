package com.example.dictionaryapp.ui.components.home_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionaryapp.R
import com.example.dictionaryapp.data.local.entities.SearchHistory
import com.example.dictionaryapp.data.local.entities.WordDbo
import com.example.dictionaryapp.repository.SearchHistoryRepository
import com.example.dictionaryapp.repository.WordRepository
import com.example.dictionaryapp.utils.NetworkResource
import com.example.dictionaryapp.utils.ResourcesProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: WordRepository,
    private val searchHistoryRepository: SearchHistoryRepository,
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {

    val searchFieldState = MutableStateFlow(SearchFieldState())

    private val networkResource =
        MutableStateFlow<NetworkResource<List<WordDbo>>>(NetworkResource.Nothing())

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _words = mutableStateOf<List<WordDbo>>(emptyList())
    val words: State<List<WordDbo>> = _words

    private val _showToast = MutableSharedFlow<String>()
    val showToast: SharedFlow<String> = _showToast.asSharedFlow()

    private var searchByWordJob: Job? = null

    init {
        viewModelScope.launch(Dispatchers.IO) {
            networkResource.collectLatest {
                when (it) {
                    is NetworkResource.Failure -> {
                        withContext(Dispatchers.Main) {
                            _isLoading.value = false
                            _words.value = it.data ?: emptyList()
                            _showToast.emit(
                                it.message
                                    ?: resourcesProvider.getString(R.string.toast_unknown_error_message)
                            )
                        }
                    }
                    is NetworkResource.Loading -> {
                        withContext(Dispatchers.Main) {
                            _isLoading.value = true
                            _words.value = it.data ?: emptyList()
                        }
                    }
                    is NetworkResource.Nothing -> {
                        withContext(Dispatchers.Main) {
                            _isLoading.value = false
                            _words.value = it.data ?: emptyList()
                        }
                    }
                    is NetworkResource.Success -> {
                        withContext(Dispatchers.Main) {
                            _isLoading.value = false
                            _words.value = it.data ?: emptyList()
                        }
                    }
                }
            }
        }
    }

    fun searchByWord() {
        val word = searchFieldState.value.value
        if (word.isBlank()) {
            viewModelScope.launch(Dispatchers.Main) {
                _words.value = emptyList()
            }
            return
        }
        searchByWordJob?.cancel()
        searchByWordJob = viewModelScope.launch(Dispatchers.IO) {
            repository.searchByWord(word).onEach {
                if (it is NetworkResource.Success) {
                    searchHistoryRepository.saveSearchQueryInSearchHistory(
                        SearchHistory(value = word, date = Date())
                    )
                }
                networkResource.emit(it)
            }.launchIn(this)
        }
    }

    fun clear() = viewModelScope.launch(Dispatchers.IO) {
        searchFieldState.update { it.copy(value = "") }
        _words.value = emptyList()
    }
}