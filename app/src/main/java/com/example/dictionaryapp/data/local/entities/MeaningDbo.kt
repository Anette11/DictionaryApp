package com.example.dictionaryapp.data.local.entities

data class MeaningDbo(
    val definitions: List<DefinitionDbo> = emptyList(),
    val partOfSpeech: String?,
)