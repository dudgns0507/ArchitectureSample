package com.github.dudgns0507.core.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : Any, V : RecyclerView.ViewHolder> :
    RecyclerView.Adapter<V>() {
    private var items: List<T> = emptyList()

    private val adapter: BaseAdapter<T, V> by lazy { this }

    override fun getItemCount(): Int {
        return items.size
    }

    fun getItem(position: Int): T {
        return items[position]
    }

    fun updateList(list: List<T>) {
        items = list
        itemChanged()
    }

    fun addList(list: List<T>) {
        items = items + list
        itemChanged()
    }

    fun clearList() {
        items = emptyList()
        itemChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun itemChanged() {
        notifyDataSetChanged()
    }
}
