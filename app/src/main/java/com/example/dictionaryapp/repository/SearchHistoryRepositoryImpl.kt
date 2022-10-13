package com.example.dictionaryapp.repository

import com.example.dictionaryapp.data.local.SearchHistoryDao
import com.example.dictionaryapp.data.local.entities.SearchHistory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchHistoryRepositoryImpl @Inject constructor(
    private val searchHistoryDao: SearchHistoryDao
) : SearchHistoryRepository {

    override suspend fun saveSearchQueryInSearchHistory(
        searchHistory: SearchHistory
    ) = searchHistoryDao.saveSearchQueryInSearchHistory(searchHistory)

    override fun getAllSearchHistory(): Flow<List<SearchHistory>> =
        searchHistoryDao.getAllSearchHistory()

    override suspend fun clearAllSearchHistory() =
        searchHistoryDao.clearAllSearchHistory()
}