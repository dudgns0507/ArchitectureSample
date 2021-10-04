package com.github.dudgns0507.domain.usecase

import com.github.dudgns0507.domain.dto.Post
import com.github.dudgns0507.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow

class PatchPostUseCase(private val repository: DataRepository) {
    suspend operator fun invoke(postId: Int, post: Post): Flow<Post> {
        return repository.patchPost(postId, post)
    }
}