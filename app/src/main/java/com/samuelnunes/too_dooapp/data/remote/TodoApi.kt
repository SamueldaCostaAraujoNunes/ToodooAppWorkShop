package com.samuelnunes.too_dooapp.data.remote

import com.samuelnunes.too_dooapp.common.Constants.BASE_URL_API
import com.samuelnunes.too_dooapp.data.remote.dto.TodoDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface TodoApi {

    companion object {
        private var instance: TodoApi? = null;
        fun getInstance(): TodoApi {
            return instance
                ?: Retrofit.Builder()
                    .baseUrl(BASE_URL_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(TodoApi::class.java).also {
                        instance = it
                    }
        }
    }

    @GET("/todos")
    suspend fun getTodos(): List<TodoDto>

    @GET("/todo/{id}")
    suspend fun getTodo(@Path("id") id: Int): TodoDto

    @POST("/todo")
    suspend fun postTodo(@Body todo: TodoDto): TodoDto

}