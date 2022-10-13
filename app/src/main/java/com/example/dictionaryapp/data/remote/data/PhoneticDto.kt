package com.example.dictionaryapp.data.remote.data

import com.example.dictionaryapp.data.local.entities.PhoneticDbo

data class PhoneticDto(
    val audio: String?,
    val license: LicenseDto?,
    val sourceUrl: String?,
    val text: String?
) {
    fun toPhoneticDbo() =
        PhoneticDbo(
            audio = audio,
            license = license?.toLicenseDbo(),
            sourceUrl = sourceUrl,
            text = text
        )
}