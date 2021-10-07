package com.github.dudgns0507.mvvm.ui.post

import androidx.recyclerview.widget.RecyclerView
import com.github.dudgns0507.core.base.OnItemClickListener
import com.github.dudgns0507.domain.dto.Comment
import com.github.dudgns0507.mvvm.databinding.CommentItemBinding

class CommentViewHolder(private val itemBinding: CommentItemBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    val clickListener: OnItemClickListener<Comment>? = null

    fun bind(position: Int, comment: Comment) {
        itemBinding.apply {
            itemBinding.root.setOnClickListener {
                clickListener?.onItemClicked(position, comment)
            }

            tvBody.text = comment.body
        }
    }
}