package com.github.dudgns0507.mvvm.ui.holder

import com.github.dudgns0507.core.base.BaseViewHolder
import com.github.dudgns0507.core.util.ext.gone
import com.github.dudgns0507.core.util.ext.visible
import com.github.dudgns0507.mvvm.databinding.LoadingItemBinding

class LoadingViewHolder(
    private val binding: LoadingItemBinding
) : BaseViewHolder<LoadingItemBinding, Boolean>(binding) {

    override fun bind(position: Int, item: Boolean) {
        when(item) {
            true -> binding.rlLoadingItem.visible()
            false -> binding.rlLoadingItem.gone()
        }
    }
}
