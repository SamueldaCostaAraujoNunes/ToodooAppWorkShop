package com.samuelnunes.too_dooapp.domain.use_case.todo

import com.samuelnunes.too_dooapp.common.Resource
import com.samuelnunes.too_dooapp.data.remote.dto.TodoDto
import com.samuelnunes.too_dooapp.data.remote.dto.toTodo
import com.samuelnunes.too_dooapp.data.repository.todo.ITodoRepository
import com.samuelnunes.too_dooapp.domain.model.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodoUseCase(private val repository: ITodoRepository) : ITodoUseCase {
    override fun getAllTodos(): Flow<Resource<List<Todo>>> {
        return repository.getAllTodos().map { res ->
            res.convertType { todos ->
                todos.map {
                    it.toTodo()
                }
            }
        }
    }

    override fun getTodo(id: Int): Flow<Resource<Todo>> {
        return repository.getTodo(id).map { res ->
            res.convertType {
                it.toTodo()
            }
        }
    }

    override fun postTodo(todo: Todo): Flow<Resource<Todo>> {
        return repository.postTodo(TodoDto.byTodo(todo)).map { res ->
            res.convertType {
                it.toTodo()
            }
        }
    }


}