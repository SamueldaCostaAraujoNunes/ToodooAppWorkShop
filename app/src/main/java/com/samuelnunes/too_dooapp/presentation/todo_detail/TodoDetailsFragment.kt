package com.samuelnunes.too_dooapp.presentation.todo_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import com.samuelnunes.too_dooapp.common.Resource
import com.samuelnunes.too_dooapp.common.extensions.backgroundByPriority
import com.samuelnunes.too_dooapp.data.local.AppDatabase
import com.samuelnunes.too_dooapp.data.local.AppDatabaseFactory
import com.samuelnunes.too_dooapp.data.remote.TodoApi
import com.samuelnunes.too_dooapp.data.repository.todo.TodoRepository
import com.samuelnunes.too_dooapp.databinding.FragmentTodoDetailsBinding
import com.samuelnunes.too_dooapp.domain.model.Todo
import com.samuelnunes.too_dooapp.domain.use_case.todo.TodoUseCase
import com.samuelnunes.too_dooapp.presentation.MainViewModel
import com.samuelnunes.too_dooapp.presentation.ScreenState


class TodoDetailsFragment : Fragment() {

    private lateinit var binding: FragmentTodoDetailsBinding
    private val args: TodoDetailsFragmentArgs by navArgs()
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var todoDetailsViewModel: TodoDetailsViewModel
    private lateinit var lastTodo: Todo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTodoDetailsBinding.inflate(inflater, container, false)
        createChipsPriorityEnum()
        return binding.root
    }

    private fun createChipsPriorityEnum() {
        binding.rgPriority.apply {
            Todo.PriorityEnum.values().forEach {
                addView(createChip(it))
            }
            isSingleSelection = true
            isSelectionRequired = true
        }
    }

    private fun createChip(priorityEnum: Todo.PriorityEnum): Chip = Chip(context)
        .apply {
            id = priorityEnum.ordinal
            text = priorityEnum.name
            isClickable = true
            isCheckable = true
            backgroundByPriority(priorityEnum)
        }

    override fun onStart() {
        super.onStart()

        val database: AppDatabase = AppDatabaseFactory.getInstance(requireContext())
        todoDetailsViewModel = ViewModelProvider(
            this,
            TodoDetailsViewModelFactory(
                TodoUseCase(
                    TodoRepository(
                        TodoApi.getInstance(),
                        database.todoDao()
                    )
                )
            )
        )[TodoDetailsViewModel::class.java]

        mainViewModel.setFabClickListener{
            val todo = todo()
            if(args.screenState == ScreenState.EDIT){
                patchTodo(todo)
            }else{
                postTodo(todo)
            }
        }
    }

    private fun patchTodo(todo: Todo) {
        todoDetailsViewModel.patchTodo(todo).observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> showLoading()
                is Resource.Success -> {
                    hideLoading()
                    Snackbar.make(
                        binding.root,
                        "Tarefa atualizada com sucesso",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                is Resource.Error -> {
                    hideLoading()
                    Snackbar.make(binding.root, it.message, Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun postTodo(todo: Todo) {
        todoDetailsViewModel.postTodo(todo).observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    hideLoading()
                    populateData(it.data)
                    Snackbar.make(
                        binding.root,
                        "Tarefa criada com sucesso",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
                is Resource.Error -> {
                    hideLoading()
                    Snackbar.make(binding.root, it.message, Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val screenState: ScreenState = args.screenState
        mainViewModel.setState(screenState)

        if(screenState == ScreenState.EDIT){
            getTodo(args.todoId)
        }else{
            lastTodo = Todo("")
        }
    }

    private fun getTodo(idTodo: Int) {
        todoDetailsViewModel.todoList(idTodo).observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    showLoading()
                }
                is Resource.Success -> {
                    hideLoading()
                    populateData(it.data)
                }
                is Resource.Error -> {
                    hideLoading()
                    Snackbar.make(binding.root, it.message, Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun populateData(todo: Todo) {
        lastTodo = todo
        binding.apply {
            etTitle.setText(todo.title)
            etContent.setText(todo.content)
            rgPriority.check(todo.priority.ordinal)
            switchFinalizado.isChecked = todo.finished
        }
    }

    private fun todo(): Todo {
        return lastTodo.copy().apply {
            title = binding.etContent.text.toString()
            priority = Todo.PriorityEnum.valueOfByOrdinal(binding.rgPriority.checkedChipId) ?: Todo.PriorityEnum.LOW
            content = binding.etContent.text.toString()
            finished = binding.switchFinalizado.isChecked
        }
    }

    private fun showLoading() {
        mainViewModel.showLoading()
        binding.root.visibility = View.INVISIBLE
    }

    private fun hideLoading() {
        mainViewModel.hideLoading()
        binding.root.visibility = View.VISIBLE
    }
}