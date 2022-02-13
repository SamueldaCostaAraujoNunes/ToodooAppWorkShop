package com.samuelnunes.too_dooapp.data.repository.todo

import com.samuelnunes.too_dooapp.common.Resource
import com.samuelnunes.too_dooapp.data.remote.dto.TodoDto
import com.samuelnunes.too_dooapp.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface ITodoRepository {

    fun getAllTodos(): Flow<Resource<List<Todo>>>

    fun getTodo(id: Int): Flow<Resource<Todo>>

    fun postTodo(todo: Todo): Flow<Resource<Todo>>

    fun patchTodo(todo: Todo): Flow<Resource<Todo>>
}