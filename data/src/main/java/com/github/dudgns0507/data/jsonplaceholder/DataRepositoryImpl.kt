package com.github.dudgns0507.data.jsonplaceholder

import com.github.dudgns0507.core.util.network.Resource
import com.github.dudgns0507.data.jsonplaceholder.model.request.ReqPostEdit
import com.github.dudgns0507.domain.dto.Comment
import com.github.dudgns0507.domain.dto.Post
import com.github.dudgns0507.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DataRepositoryImpl(private val jsonService: JsonService) : DataRepository {
    override suspend fun requestPosts(start: Int, limit: Int): Flow<Resource<List<Post>>> {
        return flow {
            val response = jsonService.requestPosts(start, limit)

            if (response.isSuccessful) {
                val body = response.body()

                body?.let { posts ->
                    emit(Resource.Success(posts.map { post -> post.toModel() }))
                } ?: kotlin.run {
                    emit(Resource.Failure(null))
                }
            } else {
                emit(Resource.Failure(null))
            }
        }
    }

    override suspend fun requestPost(postId: Int): Flow<Resource<Post>> {
        return flow {
            val response = jsonService.requestPost(postId)

            if (response.isSuccessful) {
                val body = response.body()

                body?.let { post ->
                    emit(Resource.Success(post.toModel()))
                } ?: kotlin.run {
                    emit(Resource.Failure(null))
                }
            } else {
                emit(Resource.Failure(null))
            }
        }
    }

    override suspend fun requestPostComments(postId: Int): Flow<Resource<List<Comment>>> {
        return flow {
            val response = jsonService.requestPostComments(postId)

            if (response.isSuccessful) {
                val body = response.body()

                body?.let { comments ->
                    emit(Resource.Success(comments.map { comment -> comment.toModel() }))
                } ?: kotlin.run {
                    emit(Resource.Failure(null))
                }
            } else {
                emit(Resource.Failure(null))
            }
        }
    }

    override suspend fun deletePost(postId: Int) {
        jsonService.deletePost(postId)
    }

    override suspend fun patchPost(postId: Int, newPost: Post): Flow<Resource<Post>> {
        return flow {
            val response = jsonService.patchPost(
                postId, ReqPostEdit(
                    title = newPost.title,
                    body = newPost.body
                )
            )

            if (response.isSuccessful) {
                val body = response.body()

                body?.let { post ->
                    emit(Resource.Success(post.toModel()))
                } ?: kotlin.run {
                    emit(Resource.Failure(null))
                }
            } else {
                emit(Resource.Failure(null))
            }
        }
    }
}