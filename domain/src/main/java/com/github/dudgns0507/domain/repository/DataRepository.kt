package com.github.dudgns0507.domain.repository

import com.github.dudgns0507.core.util.network.Resource
import com.github.dudgns0507.domain.dto.Comment
import com.github.dudgns0507.domain.dto.Post
import kotlinx.coroutines.flow.Flow

interface DataRepository {
    suspend fun requestPosts(
        start: Int,
        limit: Int
    ): Flow<Resource<List<Post>>>

    suspend fun requestPost(
        postId: Int
    ): Flow<Resource<Post>>

    suspend fun requestPostComments(
        postId: Int
    ): Flow<Resource<List<Comment>>>

    suspend fun deletePost(
        postId: Int
    )

    suspend fun patchPost(
        postId: Int,
        post: Post
    ): Flow<Resource<Post>>
}