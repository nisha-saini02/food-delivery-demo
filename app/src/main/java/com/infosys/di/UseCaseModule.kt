package com.infosys.di

import com.infosys.domain.repository.LocalRepository
import com.infosys.domain.repository.Repository
import com.infosys.domain.usecase.LocalUseCase
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

    @Singleton
    @Provides
    fun provideLocalUseCase(
        repository: LocalRepository
    ): LocalUseCase {
        return LocalUseCase(repository)
    }
}