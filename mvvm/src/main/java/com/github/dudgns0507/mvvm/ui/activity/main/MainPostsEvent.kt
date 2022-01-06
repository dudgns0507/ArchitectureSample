package com.github.dudgns0507.mvvm.ui.activity.main

import com.github.dudgns0507.domain.dto.PostEntity

sealed class MainPostsEvent {
    data class Read(val start: Int, val limit: Int) : MainPostsEvent()
    data class Delete(val postId: Int) : MainPostsEvent()
    data class Patch(val postId: Int, val post: PostEntity) : MainPostsEvent()
    object ReadFirst : MainPostsEvent()
    object ReadMore : MainPostsEvent()
}
