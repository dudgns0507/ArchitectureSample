package com.github.dudgns0507.mvvm.ui.post

import androidx.recyclerview.widget.RecyclerView
import com.github.dudgns0507.domain.dto.Comment
import com.github.dudgns0507.mvvm.databinding.CommentItemBinding

class CommentViewHolder(private val itemBinding: CommentItemBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(position: Int, comment: Comment) {
        itemBinding.apply {
            tvBody.text = comment.body
        }
    }
}
