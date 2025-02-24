package com.infosys.di

import com.infosys.domain.repository.Repository
import com.infosys.domain.usecase.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideUseCase(
        repository: Repository
    ): UseCase {
        return UseCase(repository)
    }
}