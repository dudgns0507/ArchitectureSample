package com.github.dudgns0507.core.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<T : Any, V : RecyclerView.ViewHolder>(diffCallback: DiffCallback<T>) :
    ListAdapter<T, V>(diffCallback) {

    init {
        diffCallback.isNewItem = { oldItem, newItem ->
            isNewItem(oldItem, newItem)
        }
        diffCallback.isNewContent = { oldItem, newItem ->
            isNewContent(oldItem, newItem)
        }
    }

    abstract fun isNewItem(oldItem: T, newItem: T): Boolean
    abstract fun isNewContent(oldItem: T, newItem: T): Boolean
}

class DiffCallback<T> : DiffUtil.ItemCallback<T>() {
    lateinit var isNewItem: (T, T) -> Boolean
    lateinit var isNewContent: (T, T) -> Boolean

    override fun areItemsTheSame(
        oldItem: T,
        newItem: T
    ): Boolean {
        return isNewItem(oldItem, newItem)
    }

    override fun areContentsTheSame(
        oldItem: T,
        newItem: T
    ): Boolean {
        return isNewContent(oldItem, newItem)
    }
}