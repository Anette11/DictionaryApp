package com.example.dictionaryapp.data.remote

import com.example.dictionaryapp.data.remote.data.WordDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class WordWebservice(
    private val httpClient: HttpClient
) {
    suspend fun searchByWord(
        word: String
    ): List<WordDto> = httpClient
        .get("https://api.dictionaryapi.dev/api/v2/entries/en/$word")
        .body()
}