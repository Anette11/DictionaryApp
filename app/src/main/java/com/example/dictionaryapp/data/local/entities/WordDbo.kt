package com.example.dictionaryapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word")
data class WordDbo(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val license: LicenseDbo?,
    val meanings: List<MeaningDbo> = emptyList(),
    val phonetics: List<PhoneticDbo> = emptyList(),
    val sourceUrls: List<String> = emptyList(),
    val word: String?
)