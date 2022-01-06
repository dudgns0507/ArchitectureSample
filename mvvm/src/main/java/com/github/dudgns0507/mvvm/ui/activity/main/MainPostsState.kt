package com.github.dudgns0507.mvvm.ui.activity.main

import com.github.dudgns0507.domain.dto.PostEntity

data class MainPostsState(
    val posts: List<PostEntity> = emptyList(),
    val start: Int = 0,
    val limit: Int = 10,
    val isLoadFinish: Boolean = false
)
