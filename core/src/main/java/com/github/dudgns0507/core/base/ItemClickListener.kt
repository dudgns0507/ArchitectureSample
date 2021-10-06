package com.github.dudgns0507.core.base

interface ItemClickListener<T> {
    fun onClick(position: Int, item: T)
}