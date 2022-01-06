package com.github.dudgns0507.core.base

import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseDiffAdapter<T : Any, V : RecyclerView.ViewHolder> :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), DiffCallBack<T> {

    private val adapter: BaseDiffAdapter<T, V> by lazy { this }
    var asyncDiffer = AsyncListDiffer(
        adapter,
        object : DiffUtil.ItemCallback<T>() {
            override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
                return isNewItem(oldItem, newItem)
            }

            override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
                return isNewContent(oldItem, newItem)
            }
        }
    )

    override fun getItemCount(): Int {
        return asyncDiffer.currentList.size
    }

    fun getItem(position: Int): T {
        return asyncDiffer.currentList[position]
    }

    fun updateList(list: List<T>) {
        asyncDiffer.submitList(list)
    }

    fun addList(list: List<T>) {
        asyncDiffer.submitList(asyncDiffer.currentList + list)
    }

    fun clearList() {
        asyncDiffer.submitList(emptyList())
    }
}