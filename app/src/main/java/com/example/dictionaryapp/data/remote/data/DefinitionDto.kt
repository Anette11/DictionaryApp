package com.example.dictionaryapp.data.remote.data

import com.example.dictionaryapp.data.local.entities.DefinitionDbo

data class DefinitionDto(
    val definition: String?,
    val example: String?,
) {
    fun toDefinitionDbo() =
        DefinitionDbo(
            definition = definition,
            example = example
        )
}