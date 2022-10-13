package com.example.dictionaryapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dictionaryapp.data.local.entities.SearchHistory
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveSearchQueryInSearchHistory(searchHistory: SearchHistory)

    @Query("SELECT * FROM search_history ORDER BY date DESC")
    fun getAllSearchHistory(): Flow<List<SearchHistory>>

    @Query("DELETE FROM search_history")
    suspend fun clearAllSearchHistory()
}