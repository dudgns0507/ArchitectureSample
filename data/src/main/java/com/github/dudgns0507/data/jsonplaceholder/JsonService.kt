package com.github.dudgns0507.data.jsonplaceholder

import com.github.dudgns0507.core.util.network.GenericError
import com.github.dudgns0507.core.util.network.ResultWrapper
import com.github.dudgns0507.data.jsonplaceholder.model.response.ResComment
import com.github.dudgns0507.data.jsonplaceholder.model.response.ResPost
import com.github.dudgns0507.data.jsonplaceholder.model.request.ReqPostEdit
import retrofit2.http.*

interface JsonService {
    @GET("posts")
    suspend fun requestPosts(
        @Query("_start") start: Int,
        @Query("_limit") limit: Int
    ): ResultWrapper<List<ResPost>, GenericError>

    @GET("posts/{postId}")
    suspend fun requestPost(
        @Path("postId") postId: Int
    ): ResultWrapper<ResPost, GenericError>

    @GET("posts/{postId}/comments")
    suspend fun requestPostComments(
        @Path("postId") postId: Int
    ): ResultWrapper<List<ResComment>, GenericError>

    @DELETE("posts/{postId}")
    suspend fun deletePost(
        @Path("postId") postId: Int
    ): ResultWrapper<String, GenericError>

    @PATCH("posts/{postId}")
    suspend fun patchPost(
        @Path("postId") postId: Int,
        @Body post: ReqPostEdit
    ): ResultWrapper<ResPost, GenericError>
}