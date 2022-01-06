package com.github.dudgns0507.domain.repository

import com.github.dudgns0507.core.util.network.Resource
import com.github.dudgns0507.domain.dto.CommentEntity
import com.github.dudgns0507.domain.dto.PostEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Call

interface DataRepository {
    suspend fun requestPosts(
        start: Int,
        limit: Int
    ): Flow<Resource<List<PostEntity>>>

    suspend fun requestPostsEx1(
        start: Int,
        limit: Int
    ): Resource<List<PostEntity>>

    suspend fun requestPostsEx2(
        start: Int,
        limit: Int
    ): List<PostEntity>

    fun requestPostsEx3(
        start: Int,
        limit: Int
    ): Call<List<PostEntity>>

    suspend fun requestPost(
        postId: Int
    ): Flow<Resource<PostEntity>>

    suspend fun requestPostComments(
        postId: Int
    ): Flow<Resource<List<CommentEntity>>>

    suspend fun deletePost(
        postId: Int
    )

    suspend fun patchPost(
        postId: Int,
        post: PostEntity
    ): Flow<Resource<PostEntity>>
}
