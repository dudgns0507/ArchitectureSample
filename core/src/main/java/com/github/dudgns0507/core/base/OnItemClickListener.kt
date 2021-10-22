package com.github.dudgns0507.core.base

interface OnItemClickListener<ITEM : Any> {
    fun onItemClicked(position: Int, item: ITEM)
}
