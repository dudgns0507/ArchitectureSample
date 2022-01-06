package com.github.dudgns0507.domain.usecase

import com.github.dudgns0507.core.util.network.Resource
import com.github.dudgns0507.domain.dto.InvalidPostException
import com.github.dudgns0507.domain.dto.PostEntity
import com.github.dudgns0507.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow

class PatchPostUseCase(private val repository: DataRepository) {
    @Throws(InvalidPostException::class)
    suspend operator fun invoke(postId: Int, post: PostEntity): Flow<Resource<PostEntity>> {
        if (post.title.isBlank()) {
            throw InvalidPostException(101, "The title of post can't be empty.")
        }
        if (post.body.isBlank()) {
            throw InvalidPostException(102, "The body of post can't be empty.")
        }
        return repository.patchPost(postId, post)
    }
}
