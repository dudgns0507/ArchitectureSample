package com.github.dudgns0507.data.jsonplaceholder

import com.github.dudgns0507.core.util.network.GenericError
import com.github.dudgns0507.core.util.network.ResultWrapper
import com.github.dudgns0507.data.jsonplaceholder.model.request.ReqPostEdit
import com.github.dudgns0507.domain.dto.Comment
import com.github.dudgns0507.domain.dto.Post
import com.github.dudgns0507.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import okhttp3.Headers
import retrofit2.Response

class DataRepositoryImpl(private val jsonService: JsonService) : DataRepository {
    override suspend fun requestPosts(start: Int, limit: Int): Flow<ResultWrapper<List<Post>, GenericError>> {
        return flow {
            val response = jsonService.requestPosts(start, limit)
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    check<List<Post>, GenericError>(response, it.map { post -> post.toModel() })
                }
                val headers = response.headers()

                try {
                    body?.let {
                        emit(ResultWrapper.Success(headers, it.map { post -> post.toModel() }))
                    } ?: kotlin.run {
                        emit(ResultWrapper.UnknownError(null))
                    }
                } catch (throwable: Throwable) {
                    emit(ResultWrapper.UnknownError(throwable))
                }
            } else {
                emit(ResultWrapper.UnknownError(null))
            }
        }
    }

    private fun <T : Any, U : Any> check(response: Response<Any>, t: T): ResultWrapper<T, U> {
        if (response.isSuccessful) {
            val body = response.body()
            val headers = response.headers()

            try {
                body?.let {
                    return ResultWrapper.Success(headers, t)
                } ?: kotlin.run {
                    return ResultWrapper.UnknownError(null)
                }
            } catch (throwable: Throwable) {
                return ResultWrapper.UnknownError(throwable)
            }
        } else {
            return ResultWrapper.UnknownError(null)
        }
    }

    override suspend fun requestPost(postId: Int): Flow<Post> {
        return jsonService.requestPost(postId).map { post ->
            post.toModel()
        }
    }

    override suspend fun requestPostComments(postId: Int): Flow<List<Comment>> {
        return jsonService.requestPostComments(postId).map { comments ->
            comments.map { comment ->
                comment.toModel()
            }
        }
    }

    override suspend fun deletePost(postId: Int) {
        jsonService.deletePost(postId)
    }

    override suspend fun patchPost(postId: Int, newPost: Post): Flow<Post> {
        return jsonService.patchPost(
            postId, ReqPostEdit(
                title = newPost.title,
                body = newPost.body
            )
        ).map { post ->
            post.toModel()
        }
    }
}