package com.github.dudgns0507.domain.repository

import com.github.dudgns0507.domain.dto.Comment
import com.github.dudgns0507.domain.dto.Post

interface DataRepository {
    suspend fun requestPosts(
        start: Int,
        limit: Int
    ): List<Post>

    suspend fun requestPost(
        postId: Int
    ): Post

    suspend fun requestPostComments(
        postId: Int
    ): List<Comment>

    suspend fun deletePost(
        postId: Int
    ): String

    suspend fun patchPost(
        postId: Int,
        post: Post
    ): Post
}