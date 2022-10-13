package com.example.dictionaryapp.data.remote.data

import com.example.dictionaryapp.data.local.entities.LicenseDbo

data class LicenseDto(
    val name: String?,
    val url: String?
) {
    fun toLicenseDbo() =
        LicenseDbo(
            name = name,
            url = url
        )
}