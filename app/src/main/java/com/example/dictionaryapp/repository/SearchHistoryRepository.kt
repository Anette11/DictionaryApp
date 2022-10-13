package com.example.dictionaryapp.repository

import com.example.dictionaryapp.data.local.entities.SearchHistory
import kotlinx.coroutines.flow.Flow

interface SearchHistoryRepository {

    suspend fun saveSearchQueryInSearchHistory(searchHistory: SearchHistory)

    fun getAllSearchHistory(): Flow<List<SearchHistory>>

    suspend fun clearAllSearchHistory()
}