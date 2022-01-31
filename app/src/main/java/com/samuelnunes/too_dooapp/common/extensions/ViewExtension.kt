package com.samuelnunes.too_dooapp.common.extensions

import android.graphics.Color
import android.view.View
import com.samuelnunes.too_dooapp.domain.model.Todo


fun View.backgroundByPriority(priority: Todo.PriorityEnum){
    setBackgroundColor(Color.parseColor(priority.color));
}

fun View.visibilityIf(isVisible: Boolean){
    visibility = if (isVisible) View.VISIBLE else View.GONE
}
