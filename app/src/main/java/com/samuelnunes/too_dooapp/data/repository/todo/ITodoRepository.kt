package com.samuelnunes.too_dooapp.data.repository.todo

import com.samuelnunes.too_dooapp.common.Resource
import com.samuelnunes.too_dooapp.data.remote.dto.TodoDto
import kotlinx.coroutines.flow.Flow

interface ITodoRepository {

    fun getAllTodos(): Flow<Resource<List<TodoDto>>>

    fun getTodo(id: Int): Flow<Resource<TodoDto>>

    fun postTodo(todo: TodoDto): Flow<Resource<TodoDto>>
}