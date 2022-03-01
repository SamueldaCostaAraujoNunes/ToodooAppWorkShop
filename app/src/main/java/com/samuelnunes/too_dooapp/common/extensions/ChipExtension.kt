package com.samuelnunes.too_dooapp.common.extensions

import android.content.res.ColorStateList
import android.graphics.Color
import com.google.android.material.chip.Chip
import com.samuelnunes.too_dooapp.domain.model.Todo

fun Chip.backgroundByPriority(priority: Todo.PriorityEnum){
    chipBackgroundColor = ColorStateList.valueOf(Color.parseColor(priority.color))
}