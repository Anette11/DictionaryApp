package com.example.dictionaryapp.data.remote.utils

import android.util.Log
import io.ktor.client.plugins.logging.*

object CustomLogger : Logger {

    override fun log(message: String) {
        Log.d("CustomLogger", message)
    }
}