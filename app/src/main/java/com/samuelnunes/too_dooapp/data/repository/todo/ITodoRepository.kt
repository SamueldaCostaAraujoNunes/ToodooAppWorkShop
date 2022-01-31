package com.samuelnunes.too_dooapp.data.repository.todo

import com.samuelnunes.too_dooapp.common.Resource
import com.samuelnunes.too_dooapp.data.remote.dto.TodoDto
import kotlinx.coroutines.flow.Flow

interface ITodoRepository {

    fun getAllTodosDto(): Flow<Resource<List<TodoDto>>>

    fun getTodoDto(id: Int): Flow<Resource<TodoDto>>

    fun postTodoDto(todo: TodoDto): Flow<Resource<TodoDto>>
}