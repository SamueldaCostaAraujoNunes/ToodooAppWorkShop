package com.samuelnunes.too_dooapp.common

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Loading<T> : Resource<T>()
    class Success<T>(data: T): Resource<T>(data = data)
    class Error<T>(message: String) : Resource<T>(message = message)

    fun <R> convertType(convertData: (T) -> R): Resource<R> {
        return when(this){
            is Success -> Success(convertData(data!!))
            is Loading -> Loading()
            is Error -> Error(message!!)
        }
    }
}