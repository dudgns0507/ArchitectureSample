package com.github.dudgns0507.data.jsonplaceholder

import com.github.dudgns0507.core.util.ext.request
import com.github.dudgns0507.core.util.network.Resource
import com.github.dudgns0507.data.jsonplaceholder.model.request.ReqPostEdit
import com.github.dudgns0507.data.jsonplaceholder.model.response.ResPost
import com.github.dudgns0507.domain.dto.Comment
import com.github.dudgns0507.domain.dto.Post
import com.github.dudgns0507.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call

class DataRepositoryImpl(private val jsonService: JsonService) : DataRepository {
    /**
     * Retrofit with Flow & Response Example
     *
     * Use sealed class 'Resource' to recognize state of response
     *
     * Resource.Loading - means start request. you can add some ui like progressbar to wait response
     * Resource.Success - means receive success and body isn't null or empty
     * Resource.Failure - means fail to receive data from server send throwable to find error type
     */
    override suspend fun requestPosts(start: Int, limit: Int): Flow<Resource<List<Post>>> {
        return flow {
            emit(Resource.Loading())
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

    /**
     * Retrofit with Response Example
     *
     * Use sealed class 'Resource' to recognize state of response
     * You can detect success or failure.
     * Can't emit Loading type like flow.
     *
     * Resource.Success - means receive success and body isn't null or empty
     * Resource.Failure - means fail to receive data from server send throwable to find error type
     */

    override suspend fun requestPostsEx1(start: Int, limit: Int): Resource<List<Post>> {
        val response = jsonService.requestPosts(start, limit)

        if (response.isSuccessful) {
            val body = response.body()

            body?.let { posts ->
                return Resource.Success(posts.map { post -> post.toModel() })
            } ?: kotlin.run {
                return Resource.Failure(null)
            }
        } else {
            return Resource.Failure(null)
        }
    }

    /**
     * Retrofit with suspend Example
     *
     * You can receive just body data.
     * It is simple way to use but hard to detect error handling
     * If additionally need header data recommend to use Response or Call class
     */

    override suspend fun requestPostsEx2(start: Int, limit: Int): List<Post> {
        return jsonService.requestPostsEx2(start, limit).map { it.toModel() }
    }

    override fun requestPostsEx3(start: Int, limit: Int): Call<List<ResPost>> {
        return jsonService.requestPostsEx3(start, limit)
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

    override suspend fun patchPost(postId: Int, post: Post): Flow<Resource<Post>> {
        return flow {
            val response = jsonService.patchPost(
                postId, ReqPostEdit(
                    title = post.title,
                    body = post.body
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