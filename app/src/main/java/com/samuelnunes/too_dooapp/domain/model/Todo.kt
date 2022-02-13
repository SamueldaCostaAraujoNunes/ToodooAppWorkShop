package com.samuelnunes.too_dooapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class Todo(
    var title: String,
    var priority: PriorityEnum = PriorityEnum.LOW,
    var content: String = "",
    var finished: Boolean = false,
    val created_at: LocalDateTime = LocalDateTime.now(),
    @PrimaryKey(autoGenerate = false)
    val id: Int = -1
){
    enum class PriorityEnum(val color: String) {
        LOW( "#00FF00"),
        MEDIUM( "#FFFF00"),
        HIGH("#FF0000");

        companion object {

            fun valueOfByOrdinal(id: Int): PriorityEnum? {
                values().forEach {
                    if(id == it.ordinal){
                        return it
                    }
                }
                return null
            }
        }
    }
}