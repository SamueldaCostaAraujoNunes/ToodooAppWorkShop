package com.samuelnunes.too_dooapp.common

sealed class Resource<T> {
    class Loading<T>: Resource<T>()
    class Success<T>(val data: T): Resource<T>()
    class Error<T>(val message: String) : Resource<T>() {
        constructor(throwable: Throwable) : this(throwable.localizedMessage ?: throwable.message ?: throwable.toString())
    }
}