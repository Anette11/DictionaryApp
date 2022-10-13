package com.example.dictionaryapp.data.local.entities

data class PhoneticDbo(
    val audio: String?,
    val license: LicenseDbo?,
    val sourceUrl: String?,
    val text: String?
)