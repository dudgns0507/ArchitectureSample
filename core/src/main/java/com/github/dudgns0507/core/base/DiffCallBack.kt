package com.github.dudgns0507.core.base

import org.w3c.dom.Comment

interface DiffCallBack<T> {
    fun isNewItem(oldItem: T, newItem: T): Boolean

    fun isNewContent(oldItem: T, newItem: T): Boolean
}