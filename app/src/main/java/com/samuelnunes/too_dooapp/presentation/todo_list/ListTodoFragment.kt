package com.samuelnunes.too_dooapp.presentation.todo_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.samuelnunes.too_dooapp.common.Resource
import com.samuelnunes.too_dooapp.data.remote.TodoApi
import com.samuelnunes.too_dooapp.data.repository.todo.TodoRepository
import com.samuelnunes.too_dooapp.databinding.FragmentListTodoBinding
import com.samuelnunes.too_dooapp.domain.use_case.todo.TodoUseCase
import com.samuelnunes.too_dooapp.presentation.MainViewModel
import timber.log.Timber

class ListTodoFragment : Fragment() {

    private lateinit var binding: FragmentListTodoBinding
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var listTodoViewModel: ListTodoViewModel
    private val mainViewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentListTodoBinding.inflate(inflater, container, false).apply {  }
        todoAdapter = TodoAdapter()
        return binding.root.apply {
            adapter = todoAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        listTodoViewModel = ViewModelProvider(this,
            ListTodoViewModelFactory(
                TodoUseCase(
                    TodoRepository(
                        TodoApi.getInstance()
                    )
                )
            )
        ).get(ListTodoViewModel::class.java)
        listTodoViewModel.todoList.observe(viewLifecycleOwner) {
            when(it){
                is Resource.Loading -> {
                    mainViewModel.showLoading()
                    Timber.d("Loading!!! " + it.data)
                }
                is Resource.Success -> {
                    mainViewModel.hideLoading()
                    Timber.d("Success!!! " + it.data)
                    todoAdapter.todos = it.data!!
                }
                is Resource.Error -> {
                    mainViewModel.hideLoading()
                    Snackbar.make(binding.root, it.message!!, Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

}