package com.example.dictionaryapp.repository

import com.example.dictionaryapp.R
import com.example.dictionaryapp.data.local.WordDao
import com.example.dictionaryapp.data.local.entities.WordDbo
import com.example.dictionaryapp.data.remote.WordWebservice
import com.example.dictionaryapp.utils.NetworkResource
import com.example.dictionaryapp.utils.ResourcesProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WordRepositoryImpl @Inject constructor(
    private val wordDao: WordDao,
    private val wordWebservice: WordWebservice,
    private val resourcesProvider: ResourcesProvider
) : WordRepository {

    override fun searchByWord(
        word: String
    ): Flow<NetworkResource<List<WordDbo>>> = flow {
        emit(NetworkResource.Loading())
        val wordsFromDatabase = wordDao.searchByWord(word)
        emit(NetworkResource.Loading(wordsFromDatabase))
        try {
            val wordsFromWebservice = wordWebservice.searchByWord(word)
                .map { wordDto -> wordDto.toWordDbo() }
            wordDao.deleteListWords(wordsFromWebservice.map { wordDbo -> wordDbo.word ?: "" })
            wordDao.saveListWords(wordsFromWebservice)
            val refreshedWordsFromDatabase = wordDao.searchByWord(word)
            emit(NetworkResource.Success(refreshedWordsFromDatabase))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(
                NetworkResource.Failure(
                    data = wordsFromDatabase,
                    message = resourcesProvider.getString(R.string.toast_error_message)
                )
            )
        }
    }
}