package com.github.dudgns0507.mvvm.di

import com.github.dudgns0507.data.jsonplaceholder.DataRepositoryImpl
import com.github.dudgns0507.data.jsonplaceholder.JsonService
import com.github.dudgns0507.domain.repository.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

@InstallIn(ActivityComponent::class)
@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun bindDataRepository(service: JsonService): DataRepository {
        return DataRepositoryImpl(service)
    }
}