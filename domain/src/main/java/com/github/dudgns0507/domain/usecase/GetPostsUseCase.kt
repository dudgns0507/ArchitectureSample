package com.github.dudgns0507.domain.usecase

import com.github.dudgns0507.domain.dto.Post
import com.github.dudgns0507.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow

class GetPostsUseCase(private val repository: DataRepository) {
    suspend operator fun invoke(start: Int, limit: Int): Flow<List<Post>> {
        return repository.requestPosts(start, limit)
    }
}