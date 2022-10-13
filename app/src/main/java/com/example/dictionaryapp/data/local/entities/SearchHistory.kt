package com.example.dictionaryapp.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "search_history")
data class SearchHistory(
    @PrimaryKey(autoGenerate = false)
    val value: String,
    val date: Date
)