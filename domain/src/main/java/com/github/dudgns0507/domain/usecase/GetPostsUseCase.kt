package com.github.dudgns0507.domain.usecase

import com.github.dudgns0507.core.util.network.Resource
import com.github.dudgns0507.data.jsonplaceholder.model.response.ResPost
import com.github.dudgns0507.domain.dto.Post
import com.github.dudgns0507.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetPostsUseCase(private val repository: DataRepository) {
    suspend operator fun invoke(start: Int, limit: Int): Flow<Resource<List<Post>>> {
        return repository.requestPosts(start, limit)
    }
}

class GetPostsUseCaseEx1(private val repository: DataRepository) {
    suspend operator fun invoke(start: Int, limit: Int): Resource<List<Post>> {
        return repository.requestPostsEx1(start, limit)
    }
}

class GetPostsUseCaseEx2(private val repository: DataRepository) {
    suspend operator fun invoke(start: Int, limit: Int): List<Post> {
        return repository.requestPostsEx2(start, limit)
    }
}

class GetPostsUseCaseEx3(private val repository: DataRepository) {
    operator fun invoke(start: Int, limit: Int): Call<List<ResPost>> {
        return repository.requestPostsEx3(start, limit)
    }
}