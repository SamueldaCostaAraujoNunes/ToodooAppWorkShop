package com.samuelnunes.too_dooapp.common.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<T: ViewBinding, I>(binding: T) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: I)
 }