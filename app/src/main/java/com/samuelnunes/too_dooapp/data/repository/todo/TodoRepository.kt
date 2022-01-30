package com.samuelnunes.too_dooapp.data.repository.todo

import com.samuelnunes.too_dooapp.common.Resource
import com.samuelnunes.too_dooapp.data.remote.TodoApi
import com.samuelnunes.too_dooapp.data.remote.dto.TodoDto
import com.samuelnunes.too_dooapp.data.repository.BaseRepository
import kotlinx.coroutines.flow.Flow

class TodoRepository(private val api: TodoApi) : BaseRepository, ITodoRepository {

    override fun getAllTodos(): Flow<Resource<List<TodoDto>>> = tryRequest{ api.getTodos() }

    override fun getTodo(id: Int): Flow<Resource<TodoDto>> = tryRequest{ api.getTodo(id) }

    override fun postTodo(todo: TodoDto): Flow<Resource<TodoDto>> = tryRequest{ api.postTodo(todo) }

}