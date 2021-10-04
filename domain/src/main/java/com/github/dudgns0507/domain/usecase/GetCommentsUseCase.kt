package com.github.dudgns0507.domain.usecase

import com.github.dudgns0507.domain.dto.Comment
import com.github.dudgns0507.domain.dto.Post
import com.github.dudgns0507.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow

class GetCommentsUseCase(private val repository: DataRepository) {
    suspend operator fun invoke(postId: Int): Flow<List<Comment>> {
        return repository.requestPostComments(postId)
    }
}