package com.github.dudgns0507.domain.usecase

import com.github.dudgns0507.core.util.network.Resource
import com.github.dudgns0507.domain.dto.PostEntity
import com.github.dudgns0507.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow

class GetPostUseCase(private val repository: DataRepository) {
    suspend operator fun invoke(postId: Int): Flow<Resource<PostEntity>> {
        return repository.requestPost(postId)
    }
}
