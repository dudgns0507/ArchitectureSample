package com.github.dudgns0507.mvvm.di

import com.github.dudgns0507.data.DataManager
import com.github.dudgns0507.data.jsonplaceholder.DataRepositoryImpl
import com.github.dudgns0507.data.jsonplaceholder.JsonService
import com.github.dudgns0507.domain.repository.DataRepository
import com.github.dudgns0507.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Provides
    @Singleton
    fun bindDataRepository(service: JsonService): DataRepository {
        return DataRepositoryImpl(service)
    }

    @Provides
    @Singleton
    fun provideDataManager(): DataManager {
        return DataManager()
    }

    @Provides
    @Singleton
    fun provideJsonUseCases(repository: DataRepository): JsonUseCases {
        return JsonUseCases(
            getPostsUseCase = GetPostsUseCase(repository),
            getPostUseCase = GetPostUseCase(repository),
            getCommentsUseCase = GetCommentsUseCase(repository),
            patchPostUseCase = PatchPostUseCase(repository),
            deletePostUseCase = DeletePostUseCase(repository)
        )
    }
}


