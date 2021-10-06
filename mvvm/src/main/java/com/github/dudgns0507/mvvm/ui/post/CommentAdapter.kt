package com.github.dudgns0507.mvvm.ui.post

import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.dudgns0507.core.base.BaseListAdapter
import com.github.dudgns0507.core.base.DiffCallback
import com.github.dudgns0507.domain.dto.Comment
import com.github.dudgns0507.mvvm.databinding.CommentItemBinding

class CommentAdapter : BaseListAdapter<Comment, CommentViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(
            CommentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(position, getItem(position))
    }

    override fun isNewItem(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.id == newItem.id
    }

    override fun isNewContent(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem == newItem
    }
}
