package com.example.dictionaryapp.repository

import com.example.dictionaryapp.data.local.entities.WordDbo
import com.example.dictionaryapp.utils.NetworkResource
import kotlinx.coroutines.flow.Flow

interface WordRepository {

    fun searchByWord(word: String): Flow<NetworkResource<List<WordDbo>>>
}