package com.infosys.di

import com.infosys.domain.datasource.DataSource
import com.infosys.domain.repository.Repository
import com.infosys.domain.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        dataSource: DataSource
    ): Repository {
        return RepositoryImpl(dataSource)
    }
}