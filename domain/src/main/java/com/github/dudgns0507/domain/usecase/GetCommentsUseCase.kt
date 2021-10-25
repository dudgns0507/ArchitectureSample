package com.github.dudgns0507.domain.usecase

import com.github.dudgns0507.core.util.network.Resource
import com.github.dudgns0507.domain.dto.Comment
import com.github.dudgns0507.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow

class GetCommentsUseCase(private val repository: DataRepository) {
    suspend operator fun invoke(postId: Int): Flow<Resource<List<Comment>>> {
        return repository.requestPostComments(postId)
    }
}
