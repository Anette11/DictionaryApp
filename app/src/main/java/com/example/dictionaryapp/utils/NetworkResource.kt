package com.example.dictionaryapp.utils

sealed class NetworkResource<T>(
    val data: T?,
    val message: String?
) {
    class Success<T>(
        data: T
    ) : NetworkResource<T>(
        data = data,
        message = null
    )

    class Failure<T>(
        data: T? = null,
        message: String
    ) : NetworkResource<T>(
        data = data,
        message = message
    )

    class Loading<T>(
        data: T? = null
    ) : NetworkResource<T>(
        data = data,
        message = null
    )

    class Nothing<T> :
        NetworkResource<T>(
            data = null,
            message = null
        )
}