package com.github.dudgns0507.mvvm.ui.main

import com.github.dudgns0507.core.base.BaseViewHolder
import com.github.dudgns0507.core.base.OnItemClickListener
import com.github.dudgns0507.mvvm.data.model.ResponsePost
import com.github.dudgns0507.mvvm.databinding.PostItemBinding

class PostViewHolder(
    private val binding: PostItemBinding,
    private val listener: OnItemClickListener<ResponsePost>
) : BaseViewHolder<PostItemBinding, ResponsePost>(binding) {

    override fun bind(position: Int, item: ResponsePost) = with(binding) {
        root.setOnClickListener {
            listener.onItemClicked(position, item)
        }

        tvTitle.text = item.title
        tvDescription.text = item.body
    }
}
