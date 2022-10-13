package com.example.dictionaryapp.data.remote.data

import com.example.dictionaryapp.data.local.entities.MeaningDbo

data class MeaningDto(
    val definitions: List<DefinitionDto> = emptyList(),
    val partOfSpeech: String?,
) {
    fun toMeaningDbo() =
        MeaningDbo(
            definitions = definitions.map { definitionDto -> definitionDto.toDefinitionDbo() },
            partOfSpeech = partOfSpeech
        )
}