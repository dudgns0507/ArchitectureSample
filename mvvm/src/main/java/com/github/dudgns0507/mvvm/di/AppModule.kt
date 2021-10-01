package com.github.dudgns0507.mvvm.di

import com.github.dudgns0507.data.DataManager
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
    fun provideDataManager(): DataManager {
        return DataManager()
    }
}


