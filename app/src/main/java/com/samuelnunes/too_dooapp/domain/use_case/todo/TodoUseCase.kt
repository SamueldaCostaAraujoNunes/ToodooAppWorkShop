package com.samuelnunes.too_dooapp.domain.use_case.todo

import com.samuelnunes.too_dooapp.common.Resource
import com.samuelnunes.too_dooapp.data.repository.todo.ITodoRepository
import com.samuelnunes.too_dooapp.domain.model.Todo
import kotlinx.coroutines.flow.Flow

class TodoUseCase(private val repository: ITodoRepository) : ITodoUseCase {

    override fun getAllTodos(): Flow<Resource<List<Todo>>> = repository.getAllTodos()

    override fun getTodo(id: Int): Flow<Resource<Todo>> = repository.getTodo(id)

    override fun postTodo(todo: Todo): Flow<Resource<Todo>> = repository.postTodo(todo)

    override fun patchTodo(todo: Todo): Flow<Resource<Todo>> = repository.patchTodo(todo)

}