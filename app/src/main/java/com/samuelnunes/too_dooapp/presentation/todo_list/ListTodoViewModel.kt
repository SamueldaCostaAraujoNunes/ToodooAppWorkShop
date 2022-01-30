package com.samuelnunes.too_dooapp.presentation.todo_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.samuelnunes.too_dooapp.common.Resource
import com.samuelnunes.too_dooapp.domain.model.Todo
import com.samuelnunes.too_dooapp.domain.use_case.todo.ITodoUseCase

class ListTodoViewModel(useCase: ITodoUseCase) : ViewModel() {

    val todoList: LiveData<Resource<List<Todo>>> = useCase.getAllTodos().asLiveData()
}