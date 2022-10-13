package com.example.dictionaryapp.di

import android.content.Context
import androidx.room.Room
import com.example.dictionaryapp.data.local.SearchHistoryDao
import com.example.dictionaryapp.data.local.WordDao
import com.example.dictionaryapp.data.local.WordDatabase
import com.example.dictionaryapp.data.local.utils.Converters
import com.example.dictionaryapp.data.local.utils.GsonParser
import com.example.dictionaryapp.data.local.utils.JsonParser
import com.example.dictionaryapp.data.remote.WordWebservice
import com.example.dictionaryapp.data.remote.utils.CustomLogger
import com.example.dictionaryapp.repository.SearchHistoryRepository
import com.example.dictionaryapp.repository.SearchHistoryRepositoryImpl
import com.example.dictionaryapp.repository.WordRepository
import com.example.dictionaryapp.repository.WordRepositoryImpl
import com.example.dictionaryapp.utils.ResourcesProvider
import com.example.dictionaryapp.utils.ResourcesProviderImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.gson.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordModule {

    @Provides
    @Singleton
    fun provideHttpClient() = HttpClient() {
        install(ContentNegotiation) {
            gson()
        }
        install(Logging) {
            logger = CustomLogger
            level = LogLevel.ALL
        }
    }

    @Provides
    @Singleton
    fun provideWordWebservice(
        httpClient: HttpClient
    ) = WordWebservice(httpClient)

    @Provides
    @Singleton
    fun provideWordRepository(
        wordDao: WordDao,
        wordWebservice: WordWebservice,
        resourcesProvider: ResourcesProvider
    ): WordRepository = WordRepositoryImpl(wordDao, wordWebservice, resourcesProvider)

    @Provides
    @Singleton
    fun provideSearchHistoryRepository(
        searchHistoryDao: SearchHistoryDao
    ): SearchHistoryRepository = SearchHistoryRepositoryImpl(searchHistoryDao)

    @Provides
    @Singleton
    fun provideWordDatabase(
        @ApplicationContext context: Context,
        converters: Converters
    ) = Room.databaseBuilder(
        context,
        WordDatabase::class.java,
        WordDatabase.name
    ).addTypeConverter(converters)
        .build()

    @Provides
    @Singleton
    fun provideWordDao(
        wordDatabase: WordDatabase
    ) = wordDatabase.wordDao

    @Provides
    @Singleton
    fun provideSearchHistoryDao(
        wordDatabase: WordDatabase
    ) = wordDatabase.searchHistoryDao

    @Provides
    @Singleton
    fun provideGson(
    ): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideJsonParser(
        gson: Gson
    ): JsonParser = GsonParser(gson)

    @Provides
    @Singleton
    fun provideConverters(
        jsonParser: JsonParser
    ): Converters = Converters(jsonParser)

    @Provides
    @Singleton
    fun provideResourcesProvider(
        @ApplicationContext context: Context
    ): ResourcesProvider = ResourcesProviderImpl(context)
}