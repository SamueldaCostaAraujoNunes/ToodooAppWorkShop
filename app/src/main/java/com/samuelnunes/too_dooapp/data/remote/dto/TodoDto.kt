package com.samuelnunes.too_dooapp.data.remote.dto

import com.samuelnunes.too_dooapp.domain.model.Todo
import java.time.LocalDateTime
import java.util.*

class TodoDto(
    var title: String,
    var plataformOrigin: String,
    var priority: String = "LOW",
    var content: String = "",
    var finished: Boolean = false,
    val created_at: LocalDateTime = LocalDateTime.now(),
    val id: Int = -1
){
    companion object {
        fun byTodo(todo: Todo): TodoDto{
            return TodoDto(
                title = todo.title,
                plataformOrigin = "Android",
                priority = byPriority(todo.priority),
                content = todo.content,
                finished = todo.finished,
                created_at = todo.created_at,
                id = todo.id
            )
        }

        private fun byPriority(priority: Todo.PriorityEnum): String {
            return when(priority){
                Todo.PriorityEnum.LOW ->"LOW"
                Todo.PriorityEnum.MEDIUM -> "MEDIUM"
                Todo.PriorityEnum.HIGH -> "HIGH"
            }
        }
    }
}

fun TodoDto.toTodo(): Todo{
    return Todo(
        title = title,
        priority = toPriority(),
        content = content,
        finished = finished,
        created_at = created_at,
        id = id
    )
}


fun TodoDto.toPriority(): Todo.PriorityEnum {
    return when(this.priority){
        "LOW" -> Todo.PriorityEnum.LOW
        "MEDIUM" -> Todo.PriorityEnum.MEDIUM
        "HIGH" -> Todo.PriorityEnum.HIGH
        else -> Todo.PriorityEnum.LOW
    }
}