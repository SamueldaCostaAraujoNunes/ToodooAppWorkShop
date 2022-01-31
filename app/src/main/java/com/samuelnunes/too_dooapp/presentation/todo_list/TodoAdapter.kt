package com.samuelnunes.too_dooapp.presentation.todo_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samuelnunes.too_dooapp.common.extensions.backgroundByPriority
import com.samuelnunes.too_dooapp.common.extensions.fineshed
import com.samuelnunes.too_dooapp.common.extensions.visibilityIf
import com.samuelnunes.too_dooapp.databinding.ItemTodoBinding
import com.samuelnunes.too_dooapp.domain.model.Todo

class TodoAdapter(private val onItemClicked: (Todo) -> Unit): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(private val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(todo: Todo){
            binding.apply {
                root.setOnClickListener{ onItemClicked(todo) }
                bar.backgroundByPriority(todo.priority)
                tvTitle.text = todo.title
                tvTitle.fineshed(todo.finished)
                tvContent.text = todo.content
                tvContent.fineshed(todo.finished)
                tvContent.visibilityIf(todo.content.isNotEmpty())
            }
        }
    }

    private var _todos: List<Todo> = listOf()
    var todos: List<Todo>
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            _todos = value
            notifyDataSetChanged()
        }
        get() = _todos

    override fun getItemCount() = _todos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = ItemTodoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) = holder.bind(todos[position])

}