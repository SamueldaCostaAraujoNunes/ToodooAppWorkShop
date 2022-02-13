package com.samuelnunes.too_dooapp.data.repository.todo

import com.samuelnunes.too_dooapp.common.Resource
import com.samuelnunes.too_dooapp.data.local.TodoDao
import com.samuelnunes.too_dooapp.data.remote.TodoApi
import com.samuelnunes.too_dooapp.data.remote.dto.TodoDto
import com.samuelnunes.too_dooapp.data.remote.dto.toTodo
import com.samuelnunes.too_dooapp.data.repository.BaseRepository
import com.samuelnunes.too_dooapp.domain.model.Todo
import kotlinx.coroutines.flow.Flow

class TodoRepository(
    private val api: TodoApi,
    private val dao: TodoDao
    ) : BaseRepository(), ITodoRepository {

    override fun getAllTodos(): Flow<Resource<List<Todo>>> {
        return tryRequest(
            query = { dao.getTodos() },
            fetch = { api.getAllTodos().map { it.toTodo() } },
            saveFetchResult = { todos -> dao.insertAllTodos(todos) }
        )
    }

    override fun getTodo(id: Int): Flow<Resource<Todo>> = tryRequest(
            query = { dao.getTodo(id) },
            fetch = { api.getTodo(id).toTodo() },
            saveFetchResult = { todo -> dao.insertTodo(todo) }
        )

    override fun postTodo(todo: Todo): Flow<Resource<Todo>> = tryRequest {
        val todoDto = TodoDto.byTodo(todo)
        api.postTodo(todoDto).toTodo()
    }

    override fun patchTodo(todo: Todo): Flow<Resource<Todo>> = tryRequest(
        query = { dao.getTodo(todo.id) },
        fetch = { api.patchTodo(TodoDto.byTodo(todo)).toTodo() },
        saveFetchResult = { todo -> dao.insertTodo(todo) }
    )

}