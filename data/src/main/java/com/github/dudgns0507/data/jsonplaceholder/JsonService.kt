package com.github.dudgns0507.data.jsonplaceholder

import com.github.dudgns0507.data.jsonplaceholder.model.request.ReqPostEdit
import com.github.dudgns0507.data.jsonplaceholder.model.response.ResComment
import com.github.dudgns0507.data.jsonplaceholder.model.response.ResPost
import kotlinx.coroutines.flow.Flow
import retrofit2.http.*

interface JsonService {
    @GET("posts")
    suspend fun requestPosts(
        @Query("_start") start: Int,
        @Query("_limit") limit: Int
    ): Flow<List<ResPost>>

    @GET("posts/{postId}")
    suspend fun requestPost(
        @Path("postId") postId: Int
    ): Flow<ResPost>

    @GET("posts/{postId}/comments")
    suspend fun requestPostComments(
        @Path("postId") postId: Int
    ): Flow<List<ResComment>>

    @DELETE("posts/{postId}")
    suspend fun deletePost(
        @Path("postId") postId: Int
    )

    @PATCH("posts/{postId}")
    suspend fun patchPost(
        @Path("postId") postId: Int,
        @Body post: ReqPostEdit
    ): Flow<ResPost>
}