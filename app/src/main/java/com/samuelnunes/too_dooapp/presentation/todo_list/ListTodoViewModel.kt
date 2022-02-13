package com.samuelnunes.too_dooapp.presentation.todo_list

import androidx.lifecycle.*
import com.samuelnunes.too_dooapp.common.Resource
import com.samuelnunes.too_dooapp.domain.model.Todo
import com.samuelnunes.too_dooapp.domain.use_case.todo.ITodoUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListTodoViewModel(useCase: ITodoUseCase) : ViewModel() {

    val todoList: LiveData<Resource<List<Todo>>> = useCase.getAllTodos().asLiveData()
}