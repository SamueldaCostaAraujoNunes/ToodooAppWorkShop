package com.samuelnunes.too_dooapp.domain.model

import java.util.*

data class Todo(
    var title: String,
    var priority: PriorityEnum = PriorityEnum.LOW,
    var content: String = "",
    var finished: Boolean = false,
    val created_at: Date = Date(),
    val id: Int = -1
){
    enum class PriorityEnum(val color: String) {
        LOW( "#00FF00"),
        MEDIUM( "#FFFF00"),
        HIGH("#FF0000")
    }
}