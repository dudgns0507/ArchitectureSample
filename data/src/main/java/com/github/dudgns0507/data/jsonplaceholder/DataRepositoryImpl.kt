package com.github.dudgns0507.data.jsonplaceholder

import com.github.dudgns0507.domain.dto.Comment
import com.github.dudgns0507.domain.dto.Post
import com.github.dudgns0507.domain.repository.DataRepository

class DataRepositoryImpl(jsonService: JsonService): DataRepository {
    override suspend fun requestPosts(start: Int, limit: Int): List<Post> {
        TODO("Not yet implemented")
    }

    override suspend fun requestPost(postId: Int): Post {
        TODO("Not yet implemented")
    }

    override suspend fun requestPostComments(postId: Int): List<Comment> {
        TODO("Not yet implemented")
    }

    override suspend fun deletePost(postId: Int): String {
        TODO("Not yet implemented")
    }

    override suspend fun patchPost(postId: Int, post: Post): Post {
        TODO("Not yet implemented")
    }
}