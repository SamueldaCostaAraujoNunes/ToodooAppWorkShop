package com.samuelnunes.too_dooapp.presentation.todo_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.samuelnunes.too_dooapp.common.Resource
import com.samuelnunes.too_dooapp.domain.model.Todo
import com.samuelnunes.too_dooapp.domain.use_case.todo.ITodoUseCase

class TodoDetailsViewModel (private val useCase: ITodoUseCase) : ViewModel() {

    fun todoList(todoId: Int): LiveData<Resource<Todo>> = useCase.getTodo(todoId).asLiveData()

    fun postTodo(todo: Todo): LiveData<Resource<Todo>> = useCase.postTodo(todo).asLiveData()

    fun patchTodo(todo: Todo): LiveData<Resource<Todo>> = useCase.patchTodo(todo).asLiveData()
}