package com.github.dudgns0507.domain.usecase

import com.github.dudgns0507.domain.repository.DataRepository

class DeletePostUseCase(private val repository: DataRepository) {
    suspend operator fun invoke(postId: Int) {
        return repository.deletePost(postId)
    }
}