package com.samuelnunes.too_dooapp.common

import java.lang.Exception

sealed class Resource<T>(
    protected val _data: T? = null,
    protected  val _message: String? = null) {

    class Loading<T> : Resource<T>()
    class Success<T>(data: T): Resource<T>(_data = data){
        val data: T get() = _data!!
    }
    class Error<T>(message: String) : Resource<T>(_message = message) {
        val message: String get() = _message!!
        constructor(exception: Exception) : this(exception.localizedMessage ?: exception.message ?: exception.toString())
        constructor(throwable: Throwable) : this(throwable.localizedMessage ?: throwable.localizedMessage ?: throwable.toString())
    }
}