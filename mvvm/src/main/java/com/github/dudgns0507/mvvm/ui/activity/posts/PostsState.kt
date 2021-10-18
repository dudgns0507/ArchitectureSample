package com.github.dudgns0507.mvvm.ui.activity.posts

import com.github.dudgns0507.domain.dto.Post

data class PostsState(
    val posts: List<Post> = emptyList(),
    val start: Int = 0,
    val limit: Int = 10,
    val isLoadFinish: Boolean = false
)
