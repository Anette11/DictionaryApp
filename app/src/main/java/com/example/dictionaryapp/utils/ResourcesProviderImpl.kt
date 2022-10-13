package com.example.dictionaryapp.utils

import android.content.Context

class ResourcesProviderImpl(
    private val context: Context
) : ResourcesProvider {

    override fun getString(resId: Int): String =
        context.resources.getString(resId)
}