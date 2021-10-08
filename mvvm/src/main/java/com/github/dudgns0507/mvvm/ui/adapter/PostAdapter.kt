package com.github.dudgns0507.mvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.dudgns0507.core.base.BaseAdapter
import com.github.dudgns0507.core.base.BaseDiffAdapter
import com.github.dudgns0507.core.base.OnItemClickListener
import com.github.dudgns0507.domain.dto.Post
import com.github.dudgns0507.mvvm.databinding.PostItemBinding
import com.github.dudgns0507.mvvm.ui.holder.PostViewHolder

/**
 * BaseAdapter
 *
 * Default RecyclerView adapter.
 *
 * getItem(position: Int) - get item with position
 * updateList(list: List<T>) - change adapter list to parameter list
 * addList(list: List<T>) - append parameter list to adapter list
 * clearList() - clear adapter list
 *
 *
 * BaseDiffAdapter
 *
 * Use DiffUtil to increase loading performance.
 * Refresh only changed data.
 *
 * Default method is same with BaseAdapter.
 *
 * isNewItem(oldItem: T, newItem: T): Boolean
 * isNewContent(oldItem: T, newItem: T): Boolean
 *
 * You should override two method for using DiffUtil
 *
 */

class PostAdapter : BaseDiffAdapter<Post, PostViewHolder>() {
    lateinit var onItemClickListener: OnItemClickListener<Post>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            PostItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(position, getItem(position))
    }

    override fun isNewItem(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun isNewContent(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }
}