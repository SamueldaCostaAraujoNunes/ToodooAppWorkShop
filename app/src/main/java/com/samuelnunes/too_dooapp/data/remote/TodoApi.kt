package com.samuelnunes.too_dooapp.data.remote

import com.samuelnunes.too_dooapp.common.Constants.BASE_URL_API
import com.samuelnunes.too_dooapp.data.remote.dto.TodoDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import com.google.gson.GsonBuilder

import com.google.gson.Gson
import com.samuelnunes.too_dooapp.data.remote.converters.DateTimeRemoteConverter
import com.samuelnunes.too_dooapp.domain.model.Todo
import java.time.LocalDateTime


interface TodoApi {

    companion object {
        private var instance: TodoApi? = null;
        private var gson: Gson = GsonBuilder()
            .registerTypeAdapter(
                LocalDateTime::class.java,
                DateTimeRemoteConverter().nullSafe())
            .create()

        fun getInstance(): TodoApi {
            return instance
                ?: Retrofit.Builder()
                    .baseUrl(BASE_URL_API)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(TodoApi::class.java).also {
                        instance = it
                    }
        }
    }

    @GET("/todos")
    suspend fun getAllTodos(): List<TodoDto>

    @GET("/todo/{id}")
    suspend fun getTodo(@Path("id") id: Int): TodoDto

    @POST("/todo")
    suspend fun postTodo(@Body todo: TodoDto): TodoDto

    @PATCH("/todo")
    suspend fun patchTodo(@Body todo: TodoDto): TodoDto

}