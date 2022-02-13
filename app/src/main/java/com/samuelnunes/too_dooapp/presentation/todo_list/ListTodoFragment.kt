package com.samuelnunes.too_dooapp.presentation.todo_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.samuelnunes.too_dooapp.common.Resource
import com.samuelnunes.too_dooapp.data.local.AppDatabase
import com.samuelnunes.too_dooapp.data.local.AppDatabaseFactory
import com.samuelnunes.too_dooapp.data.remote.TodoApi
import com.samuelnunes.too_dooapp.data.repository.todo.TodoRepository
import com.samuelnunes.too_dooapp.databinding.FragmentListTodoBinding
import com.samuelnunes.too_dooapp.domain.use_case.todo.TodoUseCase
import com.samuelnunes.too_dooapp.presentation.MainViewModel
import com.samuelnunes.too_dooapp.presentation.ScreenState

class ListTodoFragment : Fragment() {

    private lateinit var binding: FragmentListTodoBinding
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var listTodoViewModel: ListTodoViewModel
    private val mainViewModel: MainViewModel by activityViewModels()
    private val navController: NavController
        get() {
            return findNavController()
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentListTodoBinding.inflate(inflater, container, false).apply {  }
        todoAdapter = TodoAdapter {
            val direction =
                ListTodoFragmentDirections.actionGlobalDetailTodoFragment(ScreenState.EDIT, it.id)
            navController.navigate(direction)
        }
        return binding.root.apply {
            adapter = todoAdapter
        }
    }

    override fun onStart() {
        super.onStart()
        val database: AppDatabase = AppDatabaseFactory.getInstance(requireContext())

        listTodoViewModel = ViewModelProvider(this,
            ListTodoViewModelFactory(
                TodoUseCase(
                    TodoRepository(
                        TodoApi.getInstance(),
                        database.todoDao()
                    )
                )
            )
        )[ListTodoViewModel::class.java]
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.setState(ScreenState.ADD)

        mainViewModel.setFabClickListener {
            val direction = ListTodoFragmentDirections.actionGlobalDetailTodoFragment(ScreenState.SAVE)
            navController.navigate(direction)
        }

        listTodoViewModel.todoList.observe(viewLifecycleOwner) {
            when(it){
                is Resource.Loading -> mainViewModel.showLoading()
                is Resource.Success -> {
                    mainViewModel.hideLoading()
                    todoAdapter.todos = it.data
                }
                is Resource.Error -> {
                    mainViewModel.hideLoading()
                    Snackbar.make(binding.root, it.message, Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

}