package com.github.dudgns0507.mvvm.ui.holder

import com.github.dudgns0507.core.base.BaseViewHolder
import com.github.dudgns0507.core.base.OnItemClickListener
import com.github.dudgns0507.domain.dto.Post
import com.github.dudgns0507.mvvm.databinding.LoadingItemBinding
import com.github.dudgns0507.mvvm.databinding.PostItemBinding

class LoadingViewHolder(
    private val binding: LoadingItemBinding
) : BaseViewHolder<LoadingItemBinding, String>(binding) {

    override fun bind(position: Int, item: String) {
    }
}
