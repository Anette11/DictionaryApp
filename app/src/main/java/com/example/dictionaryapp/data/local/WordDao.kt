package com.example.dictionaryapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dictionaryapp.data.local.entities.WordDbo

@Dao
interface WordDao {

    @Query("SELECT * FROM word WHERE word LIKE :word || '%' ORDER BY word ASC")
    fun searchByWord(word: String): List<WordDbo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveListWords(words: List<WordDbo>)

    @Query("DELETE FROM word WHERE word IN(:words)")
    suspend fun deleteListWords(words: List<String>)
}