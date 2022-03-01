package com.samuelnunes.too_dooapp.common.extensions

import android.content.res.ColorStateList
import android.graphics.Color
import com.google.android.material.card.MaterialCardView
import com.samuelnunes.too_dooapp.domain.model.Todo

fun MaterialCardView.backgroundByPriority(priority: Todo.PriorityEnum){
    setCardBackgroundColor(ColorStateList.valueOf(Color.parseColor(priority.color)))
}