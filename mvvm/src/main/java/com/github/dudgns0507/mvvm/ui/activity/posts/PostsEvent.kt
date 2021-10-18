package com.github.dudgns0507.mvvm.ui.activity.posts

import com.github.dudgns0507.domain.dto.Post

sealed class PostsEvent {
    data class Read(val start: Int, val limit: Int): PostsEvent()
    data class Delete(val postId: Int): PostsEvent()
    data class Patch(val postId: Int, val post: Post): PostsEvent()
    object ReadFirst : PostsEvent()
    object ReadMore : PostsEvent()
}