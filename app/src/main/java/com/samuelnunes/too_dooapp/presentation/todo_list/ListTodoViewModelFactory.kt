package com.samuelnunes.too_dooapp.presentation.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.samuelnunes.too_dooapp.domain.use_case.todo.ITodoUseCase

class ListTodoViewModelFactory (private val repository: ITodoUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ListTodoViewModel::class.java)) {
            ListTodoViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}