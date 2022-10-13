package com.example.dictionaryapp.data.remote.data

import com.example.dictionaryapp.data.local.entities.WordDbo

data class WordDto(
    val license: LicenseDto?,
    val meanings: List<MeaningDto> = emptyList(),
    val phonetics: List<PhoneticDto> = emptyList(),
    val sourceUrls: List<String> = emptyList(),
    val word: String?
) {
    fun toWordDbo() =
        WordDbo(
            license = license?.toLicenseDbo(),
            meanings = meanings.map { meaningDto -> meaningDto.toMeaningDbo() },
            phonetics = phonetics.map { phoneticDto -> phoneticDto.toPhoneticDbo() },
            sourceUrls = sourceUrls,
            word = word
        )
}