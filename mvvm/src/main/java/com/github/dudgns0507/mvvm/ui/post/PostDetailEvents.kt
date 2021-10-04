package com.github.dudgns0507.mvvm.ui.post

import com.github.dudgns0507.domain.dto.Post

sealed class PostDetailEvents {
    data class Read(val postId: Int): PostDetailEvents()
    data class Delete(val postId: Int): PostDetailEvents()
    data class Patch(val postId: Int, val post: Post): PostDetailEvents()
}