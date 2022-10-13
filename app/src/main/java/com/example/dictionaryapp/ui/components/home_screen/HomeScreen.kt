package com.example.dictionaryapp.ui.components.home_screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dictionaryapp.R
import com.example.dictionaryapp.ui.MainActivity
import com.example.dictionaryapp.ui.components.WordDivider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    value: String = "",
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val searchFieldState by viewModel.searchFieldState.collectAsState()
    val words by viewModel.words
    val isLoading by viewModel.isLoading
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(key1 = true) {
        viewModel.showToast.collect {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(value) {
        if (value.isNotEmpty()) {
            viewModel.searchFieldState.update { it.copy(value = value, isHintVisible = false) }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            HomeTopAppBar(
                value = searchFieldState.value,
                isHintVisible = searchFieldState.isHintVisible,
                onValueChange = { valueChanged ->
                    viewModel.searchFieldState.update { searchFieldState ->
                        searchFieldState.copy(value = valueChanged)
                    }
                },
                onFocusChanged = { focusState ->
                    viewModel.searchFieldState.update { searchFieldState ->
                        searchFieldState.copy(isHintVisible = searchFieldState.value.isEmpty() && !focusState.isFocused)
                    }
                },
                onSearchClick = {
                    viewModel.searchByWord()
                    focusManager.clearFocus()
                },
                onClearClick = {
                    viewModel.clear()
                    focusManager.clearFocus()
                }
            )
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(horizontal = dimensionResource(id = R.dimen._8dp))
            ) {
                items(words.size) { index ->
                    WordCell(
                        wordDbo = words[index],
                        onPlayAudio = { audioUrl ->
                            scope.launch(Dispatchers.IO) {
                                (context as MainActivity).playAudio.emit(audioUrl)
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._8dp)))
                    if (index + 1 != words.size) {
                        WordDivider()
                    }
                }
            }
            if (isLoading) {
                CircularProgressIndicator(color = colorResource(id = R.color.violet))
            }
        }
    }
}