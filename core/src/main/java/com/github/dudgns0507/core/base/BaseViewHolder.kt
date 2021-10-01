package com.github.dudgns0507.core.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T : ViewDataBinding, ITEM : Any>(
    binding: T
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(position: Int, item: ITEM)
}

interface OnItemClickListener<ITEM : Any> {
    fun onItemClicked(position: Int, item: ITEM)
}