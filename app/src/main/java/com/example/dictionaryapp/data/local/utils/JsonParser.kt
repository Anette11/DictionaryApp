package com.example.dictionaryapp.data.local.utils

import java.lang.reflect.Type

interface JsonParser {

    fun <T> fromJson(json: String, type: Type): T?

    fun <T> toJson(t: T, type: Type): String?
}