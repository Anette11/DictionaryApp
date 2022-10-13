package com.example.dictionaryapp.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatDate(): String {
    val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
    return simpleDateFormat.format(this)
}