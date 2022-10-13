package com.example.dictionaryapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dictionaryapp.data.local.entities.SearchHistory
import com.example.dictionaryapp.data.local.entities.WordDbo
import com.example.dictionaryapp.data.local.utils.Converters

@Database(
    entities = [
        WordDbo::class,
        SearchHistory::class
    ],
    exportSchema = false,
    version = 1
)
@TypeConverters(Converters::class)
abstract class WordDatabase : RoomDatabase() {

    abstract val wordDao: WordDao
    abstract val searchHistoryDao: SearchHistoryDao

    companion object {
        const val name = "word_db"
    }
}