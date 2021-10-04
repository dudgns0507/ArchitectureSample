package com.github.dudgns0507.domain.repository

import com.github.dudgns0507.core.util.network.GenericError
import com.github.dudgns0507.core.util.network.ResultWrapper
import com.github.dudgns0507.domain.dto.Comment
import com.github.dudgns0507.domain.dto.Post
import kotlinx.coroutines.flow.Flow

interface DataRepository {
    suspend fun requestPosts(
        start: Int,
        limit: Int
    ): Flow<ResultWrapper<List<Post>, GenericError>>

    suspend fun requestPost(
        postId: Int
    ): Flow<Post>

    suspend fun requestPostComments(
        postId: Int
    ): Flow<List<Comment>>

    suspend fun deletePost(
        postId: Int
    )

    suspend fun patchPost(
        postId: Int,
        post: Post
    ): Flow<Post>
}