package com.github.dudgns0507.data.jsonplaceholder

import com.github.dudgns0507.data.jsonplaceholder.model.request.ReqPostEdit
import com.github.dudgns0507.domain.dto.Comment
import com.github.dudgns0507.domain.dto.Post
import com.github.dudgns0507.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataRepositoryImpl(private val jsonService: JsonService) : DataRepository {
    override suspend fun requestPosts(start: Int, limit: Int): Flow<List<Post>> {
        return jsonService.requestPosts(start, limit).map { posts ->
            posts.map { post ->
                post.toModel()
            }
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
        return jsonService.patchPost(postId, ReqPostEdit(
            title = newPost.title,
            body = newPost.body
        )).map { post ->
            post.toModel()
        }
    }
}