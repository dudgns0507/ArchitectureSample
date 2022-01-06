package com.github.dudgns0507.mvvm.ui.holder

import com.github.dudgns0507.core.base.BaseViewHolder
import com.github.dudgns0507.core.base.OnItemClickListener
import com.github.dudgns0507.domain.dto.PostEntity
import com.github.dudgns0507.mvvm.databinding.PostItemBinding

class PostViewHolder(
    private val binding: PostItemBinding,
    private val listener: OnItemClickListener<PostEntity>
) : BaseViewHolder<PostItemBinding, PostEntity>(binding) {

    override fun bind(position: Int, item: PostEntity) = with(binding) {
        root.setOnClickListener {
            listener.onItemClicked(position, item)
        }

        tvTitle.text = "${item.id}. ${item.title}"
        tvDescription.text = item.body
    }
}
